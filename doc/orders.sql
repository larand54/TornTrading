USE [tt_portal]
GO

/****** Object:  Table [dbo].[orders]    Script Date: 02/28/2017 12:46:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[version] [bigint] NOT NULL,
	[currency] [varchar](3) NOT NULL,
	[customer] [varchar](50) NOT NULL,
	[dateCreated] [datetime2](7) NOT NULL,
	[destination] [varchar](80) NOT NULL,
	[lengthDescr] [varchar](50) NOT NULL,
	[orderNo] [varchar](20) NOT NULL,
	[packetSize] [varchar](15) NOT NULL,
	[period] [varchar](4) NOT NULL,
	[price] [numeric](19, 2) NOT NULL,
	[product] [varchar](100) NOT NULL,
	[quantity] [int] NOT NULL,
	[sawMill] [varchar](80) NOT NULL,
	[status] [varchar](11) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


