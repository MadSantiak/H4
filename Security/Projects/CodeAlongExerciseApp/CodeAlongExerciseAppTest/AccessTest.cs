using Bunit;
using Bunit.TestDoubles;
using CodeAlongExerciseApp.Components.Pages;

namespace CodeAlongExerciseAppTest
{
    public class AccessTest
    {
        [Fact]
        public void TestNotAuthorizedCode()
        {
            // Tests that the user is, based on the code on the page, isn't deemed authorized (i.e. they DO NOT have the Admin role).
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();
            authContext.SetAuthorized("");

            // Act
            var cut = ctx.RenderComponent<Page2>();
            var instance = cut.Instance;
            bool isAuthorized = instance.isAuthorized;

            // Assert
            Assert.False(isAuthorized);
        }


        [Fact]
        public void TestAuthenticatedCode()
        {
            // Tests the code that checks if the user is authenticated (i.e. logged in)
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();
            authContext.SetAuthorized("");

            // Act
            var cut = ctx.RenderComponent<Page2>();
            var instance = cut.Instance;
            bool isAuthenticated = instance.isAuthenticated;

            // Assert
            Assert.True(isAuthenticated);
        }

        [Fact]
        public void TestViewAuthorized()
        {
            // Tests if the user can see the expected contents when accessing Page3 while they are authorized to see.
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();
            authContext.SetAuthorized("");

            // Act
            var cut = ctx.RenderComponent<Page3>();

            // Assert
            cut.MarkupMatches(@"<h3>Page3</h3><div>
            You were AUTHORIZED
        </div>");
        }

        [Fact]
        public void TestViewNotAuthorized()
        {
            // Tests if the user can see the expected contents when accessing Page3 while they are NOT authorized to see.
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();

            // Act
            var cut = ctx.RenderComponent<Page3>();

            // Assert
            cut.MarkupMatches(@"<h3>Page3</h3><div>
            NOT AUTHORIZED!
        </div>");
        }

        [Fact]
        public void TestViewRoleAdmin()
        {
            // Tests that a user with the Admin role can see the expected contents of the Admin page (page4).
            // Arrange
            using var ctx = new TestContext();

            var authContext = ctx.AddTestAuthorization();
            authContext.SetRoles("Admin");

            // Act
            var cut = ctx.RenderComponent<Page4>();

            // Assert
            cut.MarkupMatches(@"<h3>Page4</h3>You're admin!");
        }
    }
}
