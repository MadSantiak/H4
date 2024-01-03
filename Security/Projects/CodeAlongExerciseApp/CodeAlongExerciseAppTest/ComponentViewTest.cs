using Bunit;
using Bunit.TestDoubles;
using CodeAlongExerciseApp;
using CodeAlongExerciseApp.Components.Pages;
using CodeAlongExerciseApp.Data;

namespace CodeAlongExerciseAppTest;
public class ComponentViewTest
{
    [Fact]
    public void TestViewAlone()
    {
        // Simpel test of the contents of a simple page (Page2).
        // Arrange
        using var ctx = new TestContext();
        var authContext = ctx.AddTestAuthorization();

        // Act
        var cut = ctx.RenderComponent<Page2>();

        // Assert
        cut.MarkupMatches(@"<h3>Page2</h3>");
    }
}
