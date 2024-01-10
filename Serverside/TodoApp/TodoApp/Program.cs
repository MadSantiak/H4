using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using System.Security.Authentication;
using TodoApp.Code;
using TodoApp.Components;
using TodoApp.Components.Account;
using TodoApp.Data;
using TodoApp.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

builder.Services.AddCascadingAuthenticationState();
builder.Services.AddScoped<IdentityUserAccessor>();
builder.Services.AddScoped<IdentityRedirectManager>();
builder.Services.AddScoped<AuthenticationStateProvider, IdentityRevalidatingAuthenticationStateProvider>();
builder.Services.AddSingleton<EncryptionHandler>();

builder.Services.AddAuthentication(options =>
    {
        options.DefaultScheme = IdentityConstants.ApplicationScheme;
        options.DefaultSignInScheme = IdentityConstants.ExternalScheme;
    })
    .AddIdentityCookies();

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection") ?? throw new InvalidOperationException("Connection string 'DefaultConnection' not found.");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseSqlServer(connectionString));
builder.Services.AddDatabaseDeveloperPageExceptionFilter();

builder.Services.AddIdentityCore<ApplicationUser>(options => options.SignIn.RequireConfirmedAccount = true)
    .AddEntityFrameworkStores<ApplicationDbContext>()
    .AddSignInManager()
    .AddDefaultTokenProviders();

builder.Services.AddSingleton<IEmailSender<ApplicationUser>, IdentityNoOpEmailSender>();

// Add authentication.
builder.Services.AddAuthorization(options =>
{
    options.AddPolicy("AuthenticatedUser", policy =>
    {
        policy.RequireAuthenticatedUser();
    });
});

// Set up secondary database for storying non-Identity data.
var todoConnectionString = builder.Configuration.GetConnectionString("TodoConnection") ?? throw new InvalidOperationException("Connection string 'TodoConnection' not found.");
builder.Services.AddDbContext<TodoDbContext>(options =>
    options.UseSqlServer(todoConnectionString));

// Allow only HTTPS Kestrel (i.e. disallow IIS)
builder.WebHost.UseKestrel((context, serverOptions) =>
{
    serverOptions.Configure(context.Configuration.GetSection("Kestrel"))
    .Endpoint("HTTPS", listenOptions =>
    {
        listenOptions.HttpsOptions.SslProtocols = SslProtocols.Tls12;
    });
});

builder.Services.AddDataProtection();

string kestrelPass = builder.Configuration.GetValue<string>("KestrelPassword");
builder.Configuration.GetSection("Kestrel:endpoints:Https:Certificate:password").Value = kestrelPass;
// Added the folder to the solution for ease.
var app = builder.Build();

////////////////////////////////////////////
//////////////// END OF BUILDER ////////////
////////////////////////////////////////////

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseMigrationsEndPoint();
}
else
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();
app.UseAntiforgery();

app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

// Add additional endpoints required by the Identity /Account Razor components.
app.MapAdditionalIdentityEndpoints();

app.Run();
