USE [TodoDB]
GO

/****** Object:  Table [dbo].[TodoItem]    Script Date: 11-01-2024 12:38:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TodoItem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user] [nvarchar](max) NOT NULL,
	[name] [nvarchar](max) NOT NULL,
	[status] [nvarchar](max) NULL,
	[checksum] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Todolist] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

