USE [tt_portal]
GO

/****** Object:  Table [dbo].[quick_request]    Script Date: 02/28/2017 12:47:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[quick_request](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[version] [bigint] NOT NULL,
	[contactEmail] [varchar](100) NOT NULL,
	[contact_person] [varchar](50) NOT NULL,
	[contactPhone] [varchar](25) NULL,
	[specReq] [varchar](1000) NOT NULL,
	[title] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


