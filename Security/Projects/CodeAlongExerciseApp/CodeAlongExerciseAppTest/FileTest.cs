using Bunit;
using Bunit.TestDoubles;
using CodeAlongExerciseApp.Components.Pages;
using Humanizer;
using System;
using System.Collections.Generic;
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
            // rootPath = rootPath.Replace("CodeAlongExerciseAppTest\\bin\\Debug\\net8.0", "CodeAlongExerciseApp");
            string serverPath = Path.Combine(rootPath, "wwwroot", "access_log.txt");
            string wwwRootPath = Path.Combine(rootPath, "wwwroot");

            // Check if wwwroot directory exists; if not, create it
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

            string[] fileLines = await File.ReadAllLinesAsync(filePath);
            string[] serverLines = await File.ReadAllLinesAsync(serverPath);

            // Act
            var cut = ctx.RenderComponent<Page3>();

            string[] fileLinesNew = await File.ReadAllLinesAsync(filePath);
            string[] serverLinesNew = await File.ReadAllLinesAsync(serverPath);
            // Assert
            Assert.True(fileLinesNew.Length > fileLines.Length);
            Assert.True(serverLinesNew.Length >  serverLines.Length);
        }

    }
}
