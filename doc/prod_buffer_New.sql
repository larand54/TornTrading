USE [TT_SYS]
GO

/****** Object:  Table [dbo].[LOBuffertv2]    Script Date: 2017-03-03 10:19:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[LOBuffertv2](
	[ID] [int] NOT NULL, -- row id
	[sawMill] [varchar](80) NULL,
	[status][varchar](10) NULL,
	
	[LOBuffertNo] [int] NOT NULL, -- kopplat til en leverantör
	--[PkgArticleNo] [int] NULL, -- används ej
	[Produkt] [varchar](150) NULL, -- produkt beskrivning
	[Length] [varchar](25) NULL, -- längdbeskrivning
	[ProductNo] [int] NULL, -- id för produkt, relaterar tabell product
	--[Package_Size] [int] NULL, -- används ej
	--[ActualLengthMM] [float] NULL, -- används ej
	--[PackageSizeName] [varchar](50) NULL, -- används ej
	
	[volume_available] [float] NULL,-- ursprunglig tillgänglig volym
	[OnOrder] [float] NULL, -- kontrakterad volym = bokat
	[volumeOffered] [float] NULL, -- volym offererat
	[volume_rest] [float] NULL, -- aktuell tillgänglig volym. Ursprunglig - kontrakterat
	[volume_rest_incl_offers] [float] NULL, --  Ursprunglig - (kontrakterat + offererat)
	
	
	[Delivered] [float] NULL, -- levererat av ursprunglig volym
	
	[MakeInquiry] [float] NULL, -- används ej
	[Changed] [int] NULL, -- används ej
	[Appid] [int] NULL, -- används ej
	[currency] [varchar](3) NULL, -- 
	[price] numeric(19,2) null,
	[volumeUnit] [varchar](6) NULL, -- 
	[week_end]  [varchar](255) NULL, -- 
	[week_start] [varchar](255) NULL, -- 
	
	[Period1] [float] NULL, 
	[Period2] [float] NULL,
	[Period3] [float] NULL,
	[Period4] [float] NULL,
	[Period5] [float] NULL,
	[Period6] [float] NULL,
	[Period7] [float] NULL,
	[Period8] [float] NULL,
	[Period9] [float] NULL,
	[Period10] [float] NULL,
 CONSTRAINT [PK_LOBuffertv2_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


