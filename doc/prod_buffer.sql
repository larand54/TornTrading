USE [tt_portal]
GO

/****** Object:  Table [dbo].[prod_buffer]    Script Date: 02/28/2017 12:47:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[prod_buffer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[version] [bigint] NOT NULL,
	[availw01] [int] NOT NULL,
	[availw02] [int] NOT NULL,
	[availw03] [int] NOT NULL,
	[availw04] [int] NOT NULL,
	[availw05] [int] NOT NULL,
	[availw06] [int] NOT NULL,
	[availw07] [int] NOT NULL,
	[availw08] [int] NOT NULL,
	[availw09] [int] NOT NULL,
	[availw10] [int] NOT NULL,
	[currency] [varchar](255) NOT NULL,
	[length] [float] NOT NULL,
	[price] [numeric](19, 2) NOT NULL,
	[product] [varchar](255) NOT NULL,
	[saw_mill] [varchar](255) NOT NULL,
	[status] [varchar](10) NOT NULL,
	[volume_available] [float] NOT NULL,
	[volume_booked] [float] NOT NULL,
	[volume_offered] [float] NOT NULL,
	[volume_rest] [float] NOT NULL,
	[volume_unit] [varchar](255) NOT NULL,
	[week_end] [varchar](255) NOT NULL,
	[week_start] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


