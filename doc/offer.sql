USE [tt_portal]
GO

/****** Object:  Table [dbo].[offer]    Script Date: 02/28/2017 12:45:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[offer](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[version] [bigint] NOT NULL,
	[city] [varchar](50) NULL,
	[company] [varchar](50) NULL,
	[contactEmail] [varchar](100) NULL,
	[contact_person] [varchar](50) NULL,
	[contactPhone] [varchar](25) NULL,
	[country] [varchar](20) NULL,
	[currency] [varchar](3) NOT NULL,
	[dateCreated] [datetime2](7) NOT NULL,
	[grade] [varchar](8) NOT NULL,
	[kd] [varchar](4) NOT NULL,
	[lengthDescr] [varchar](50) NOT NULL,
	[mill_offerid] [int] NOT NULL,
	[price] [numeric](19, 2) NOT NULL,
	[product] [varchar](100) NOT NULL,
	[requestid] [int] NULL,
	[sawMill] [varchar](80) NULL,
	[status] [varchar](10) NOT NULL,
	[terms_of_delivery] [varchar](18) NOT NULL,
	[volumeOffered] [float] NOT NULL,
	[volumeUnit] [varchar](6) NOT NULL,
	[weekEnd] [varchar](4) NULL,
	[weekStart] [varchar](4) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


