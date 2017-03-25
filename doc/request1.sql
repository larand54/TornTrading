USE [tt_portal]
GO

/****** Object:  Table [dbo].[request1]    Script Date: 02/28/2017 12:48:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[request1](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[version] [bigint] NOT NULL,
	[city] [varchar](50) NOT NULL,
	[company] [varchar](50) NOT NULL,
	[contactEmail] [varchar](100) NOT NULL,
	[contact_person] [varchar](50) NOT NULL,
	[contactPhone] [varchar](25) NULL,
	[country] [varchar](20) NOT NULL,
	[credit_rate] [int] NOT NULL,
	[dateCreated] [datetime2](7) NOT NULL,
	[free_text] [varchar](1000) NOT NULL,
	[fsc] [bit] NOT NULL,
	[kd] [varchar](4) NOT NULL,
	[length] [varchar](80) NOT NULL,
	[offerid] [int] NULL,
	[quality] [varchar](8) NOT NULL,
	[species] [varchar](20) NOT NULL,
	[status] [varchar](12) NOT NULL,
	[terms_of_delivery] [varchar](18) NOT NULL,
	[thickness] [float] NOT NULL,
	[volume_requested] [float] NOT NULL,
	[week_end] [varchar](4) NOT NULL,
	[week_start] [varchar](4) NOT NULL,
	[width] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


