USE [TodoDB]
GO

/****** Object:  Table [dbo].[Cpr]    Script Date: 11-01-2024 12:37:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Cpr](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cpr] [nvarchar](max) NOT NULL,
	[cpr_ending] [nvarchar](4) NOT NULL,
	[user] [nvarchar](max) NOT NULL,
	[address] [nvarchar](max) NULL,
 CONSTRAINT [PK_Cpr] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

