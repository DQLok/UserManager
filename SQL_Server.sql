USE [UserManagementLab2]
GO
/****** Object:  Table [dbo].[tblPromotions]    Script Date: 7/13/2021 10:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPromotions](
	[promotionID] [int] IDENTITY(1,1) NOT NULL,
	[typeID] [varchar](10) NULL,
	[userID] [varchar](50) NULL,
	[rank] [int] NULL,
	[assignmentdate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[promotionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 7/13/2021 10:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [varchar](10) NOT NULL,
	[rolename] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 7/13/2021 10:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[statusID] [varchar](10) NOT NULL,
	[statusname] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTypePromotions]    Script Date: 7/13/2021 10:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTypePromotions](
	[typeID] [varchar](10) NOT NULL,
	[typename] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 7/13/2021 10:02:33 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [varchar](80) NULL,
	[email] [varchar](50) NULL,
	[phone] [char](10) NULL,
	[photo] [varchar](50) NULL,
	[roleID] [varchar](10) NULL,
	[statusID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblPromotions] ON 

INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (1, N't1', N'se1', 5, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (2, N't1', N'se1', 5, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (3, N't1', N'se1', 5, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (4, N't1', N'se1', 5, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (5, N't1', N'se1', 5, CAST(N'2021-06-10' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (6, N't1', N'se20', 5, CAST(N'2021-06-09' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (7, N't1', N'khanhkk', 5, CAST(N'2021-06-11' AS Date))
INSERT [dbo].[tblPromotions] ([promotionID], [typeID], [userID], [rank], [assignmentdate]) VALUES (8, N't1', N'khanhkk', 5, CAST(N'2021-06-11' AS Date))
SET IDENTITY_INSERT [dbo].[tblPromotions] OFF
GO
INSERT [dbo].[tblRoles] ([roleID], [rolename]) VALUES (N'523', N'customer')
INSERT [dbo].[tblRoles] ([roleID], [rolename]) VALUES (N'r1', N'admin')
INSERT [dbo].[tblRoles] ([roleID], [rolename]) VALUES (N'r2', N'sub')
GO
INSERT [dbo].[tblStatus] ([statusID], [statusname]) VALUES (N's1', N'active')
INSERT [dbo].[tblStatus] ([statusID], [statusname]) VALUES (N's2', N'inactive')
GO
INSERT [dbo].[tblTypePromotions] ([typeID], [typename]) VALUES (N't1', N'Promotion 1')
INSERT [dbo].[tblTypePromotions] ([typeID], [typename]) VALUES (N't2', N'Promotion 2')
INSERT [dbo].[tblTypePromotions] ([typeID], [typename]) VALUES (N't3', N'Promotion 3')
GO
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'khanh123', N'tieunhi', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'nhi@gmail.com', N'0123456789', N'', N'r1', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'khanhkk', N'khnahabc', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'123@gmail.com', N'0123456789', NULL, N'523', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'khanhkt', N'khanhdaica', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'abc@gmail.com', N'0123456789', N'khanhkt.png', N'r2', N's2')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se1', N'diep quoc loc', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', N'loc@gmail.com', N'0123456789', N'se1.jpg', N'r1', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se10', N'Banh Duc Hieu', N'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', N'hieu@gmail.com', N'0123456789', N'', N'r2', N's2')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se11', N'diep', N'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', N'loc@gmail.com', N'0123456789', N'', N'r2', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se2', N'hieu', N'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', N'hieu@gmail.com', N'9876543210', N'se2.png', N'r2', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se20', N'loc', N'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', N'loc@gmail.com', N'0123456789', N'', N'r2', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se3', N'loooooo', N'4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', N'llll@gmail.com', N'0123456789', N'', N'r2', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se4', N'llllll', N'4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a', N'loooo@gmail.com', N'9876543210', N'', N'r1', N's1')
INSERT [dbo].[tblUsers] ([userID], [username], [password], [email], [phone], [photo], [roleID], [statusID]) VALUES (N'se5', N'Locloc', N'ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d', N'loc@gmail.com', N'0123456789', N'se5se3.jpg', N'r2', N's1')
GO
ALTER TABLE [dbo].[tblPromotions]  WITH CHECK ADD FOREIGN KEY([typeID])
REFERENCES [dbo].[tblTypePromotions] ([typeID])
GO
ALTER TABLE [dbo].[tblPromotions]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([statusID])
REFERENCES [dbo].[tblStatus] ([statusID])
GO
