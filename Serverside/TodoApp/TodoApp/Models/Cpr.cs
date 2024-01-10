using System;
using System.Collections.Generic;

namespace TodoApp.Models;

public partial class Cpr
{
    public int Id { get; set; }

    public string Cpr1 { get; set; } = null!;

    public string CprEnding { get; set; } = null!;

    public string User { get; set; } = null!;

    public string? Address { get; set; }
}
