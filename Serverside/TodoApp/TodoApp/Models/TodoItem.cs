using System;
using System.Collections.Generic;

namespace TodoApp.Models;

public partial class TodoItem
{
    public int Id { get; set; }

    public string User { get; set; } = null!;

    public string Name { get; set; } = null!;

    public string? Status { get; set; }
}
