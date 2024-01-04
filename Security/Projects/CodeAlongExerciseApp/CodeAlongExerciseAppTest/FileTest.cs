using Bunit;
using Bunit.TestDoubles;
using CodeAlongExerciseApp.Components.Pages;
using Humanizer;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodeAlongExerciseAppTest
{
    public class FileTest
    {
        [Fact]
        public async Task TestAccessLogWrite()
        {
            // Tests that the Access Log is correctly opened and written to when Page 3 is accessed.
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();
            string userDirectory = Environment.GetFolderPath(Environment.SpecialFolder.UserProfile);
            string filePath = Path.Combine(userDirectory, "access_log.txt");

            
            string rootPath = System.IO.Directory.GetCurrentDirectory();
            string serverPath = Path.Combine(rootPath, "wwwroot", "access_log.txt");
            string wwwRootPath = Path.Combine(rootPath, "wwwroot");

            // Check if wwwroot directory exists; if not, create it
            // This is necessary as the test actually runs the "page3" in its own directory, so the serverside file isn't accessed there, as it uses GetCurrentDirectory().
            // To avoid errors, we therefor have to manually ensure wwwroot is present in the Test-projects folder.
            if (!Directory.Exists(wwwRootPath))
            {
                Directory.CreateDirectory(wwwRootPath);
            }

            if (!File.Exists(filePath)) { 
                await File.AppendAllTextAsync(filePath, "");
            }
            if (!File.Exists(serverPath))
            {
                await File.AppendAllTextAsync(serverPath, "");
            }

            // Get current file length, for later comparison - to ensure the user was "logged".
            string[] fileLines = await File.ReadAllLinesAsync(filePath);
            string[] serverLines = await File.ReadAllLinesAsync(serverPath);

            // Act
            var cut = ctx.RenderComponent<Page3>();

            // Get new file length
            string[] fileLinesNew = await File.ReadAllLinesAsync(filePath);
            string[] serverLinesNew = await File.ReadAllLinesAsync(serverPath);

            // Assert
            Assert.True(fileLinesNew.Length > fileLines.Length);
            Assert.True(serverLinesNew.Length >  serverLines.Length);
        }

        [Fact]
        public async Task TestAccessLogPrint()
        {
            // Arrange
            using var ctx = new TestContext();
            var authContext = ctx.AddTestAuthorization();
            authContext.SetRoles("Admin");
            authContext.SetAuthorized("");

            string rootPath = System.IO.Directory.GetCurrentDirectory();
            string serverPath = Path.Combine(rootPath, "wwwroot", "access_log.txt");
            string wwwRootPath = Path.Combine(rootPath, "wwwroot");

            // Check if wwwroot directory exists; if not, create it
            // This is necessary as the test actually runs the "page3" in its own directory, so the serverside file isn't accessed there, as it uses GetCurrentDirectory().
            // To avoid errors, we therefor have to manually ensure wwwroot is present in the Test-projects folder.
            if (!Directory.Exists(wwwRootPath))
            {
                Directory.CreateDirectory(wwwRootPath);
            }
            
            // Act
            var cut = ctx.RenderComponent<Page3>();
            string? fileContent = await System.IO.File.ReadAllTextAsync(serverPath);

            // Insert delay to ensure pages "textarea" tag has had sufficient time to be updated with the updated access log, as it otherwise gives a false-negative.
            Task.Delay(5000).Wait();
            string? tagValue = cut.Find("textarea").GetAttribute("value");

            // Assert
            Assert.Equal(fileContent, tagValue);
        }
    }
}
