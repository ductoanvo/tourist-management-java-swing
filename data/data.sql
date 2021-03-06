CREATE DATABASE [QLDuLich]
GO
USE [QLDuLich]
GO
/****** Object:  Table [dbo].[ChuyenDi]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenDi](
	[maChuyenDi] [char](8) NOT NULL,
	[maDiemXuatPhat] [char](8) NULL,
	[maDiemDen] [char](8) NULL,
	[ngayGioDi] [datetime] NULL,
	[ngayGioDen] [datetime] NULL,
	[bienSoXe] [char](11) NULL,
PRIMARY KEY CLUSTERED 
(
	[maChuyenDi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DatVe]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DatVe](
	[maKhachHang] [char](8) NULL,
	[maChuyenDi] [char](8) NULL,
	[maNhanVien] [char](8) NULL,
	[ngayDatVe] [datetime] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiemDen]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiemDen](
	[maDiemDen] [char](8) NOT NULL,
	[tenDiemDen] [nvarchar](100) NOT NULL,
	[tenTinh] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDiemDen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiemXuatPhat]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiemXuatPhat](
	[maDiemXuatPhat] [char](8) NOT NULL,
	[tenDiemXuatPhat] [nvarchar](100) NOT NULL,
	[tenTinh] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[maDiemXuatPhat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [char](8) NOT NULL,
	[hoKhachHang] [nvarchar](20) NOT NULL,
	[tenKhachHang] [nvarchar](80) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[soCMND] [varchar](12) NOT NULL,
	[soDienThoai] [char](10) NOT NULL,
	[email] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/20/2021 10:58:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [char](8) NOT NULL,
	[tenNhanVien] [nvarchar](100) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[cmnd] [varchar](12) NOT NULL,
	[soDienThoai] [char](10) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChuyenDi] ([maChuyenDi], [maDiemXuatPhat], [maDiemDen], [ngayGioDi], [ngayGioDen], [bienSoXe]) VALUES (N'CD000001', N'DXP00001', N'DD000001', CAST(N'2021-05-20T15:50:00.000' AS DateTime), CAST(N'2021-05-20T17:35:00.000' AS DateTime), N'71-B1 68555')
INSERT [dbo].[ChuyenDi] ([maChuyenDi], [maDiemXuatPhat], [maDiemDen], [ngayGioDi], [ngayGioDen], [bienSoXe]) VALUES (N'CD000002', N'DXP00002', N'DD000003', CAST(N'2021-05-20T21:00:00.000' AS DateTime), CAST(N'2021-05-21T06:30:00.000' AS DateTime), N'70-G1 20001')
INSERT [dbo].[ChuyenDi] ([maChuyenDi], [maDiemXuatPhat], [maDiemDen], [ngayGioDi], [ngayGioDen], [bienSoXe]) VALUES (N'CD000003', N'DXP00003', N'DD000002', CAST(N'2021-05-20T21:00:00.000' AS DateTime), CAST(N'2021-05-21T09:30:00.000' AS DateTime), N'86-B1 99985')
GO
INSERT [dbo].[DatVe] ([maKhachHang], [maChuyenDi], [maNhanVien], [ngayDatVe]) VALUES (N'KH000006', N'CD000003', N'NV000002', CAST(N'2021-05-01T15:23:53.000' AS DateTime))
INSERT [dbo].[DatVe] ([maKhachHang], [maChuyenDi], [maNhanVien], [ngayDatVe]) VALUES (N'KH000007', N'CD000003', N'NV000002', CAST(N'2021-05-01T09:01:01.000' AS DateTime))
INSERT [dbo].[DatVe] ([maKhachHang], [maChuyenDi], [maNhanVien], [ngayDatVe]) VALUES (N'KH000002', N'CD000001', N'NV000001', CAST(N'2021-04-30T22:04:33.000' AS DateTime))
INSERT [dbo].[DatVe] ([maKhachHang], [maChuyenDi], [maNhanVien], [ngayDatVe]) VALUES (N'KH000003', N'CD000002', N'NV000004', CAST(N'2021-05-20T23:52:22.000' AS DateTime))
INSERT [dbo].[DatVe] ([maKhachHang], [maChuyenDi], [maNhanVien], [ngayDatVe]) VALUES (N'KH000001', N'CD000002', N'NV000003', CAST(N'2021-05-19T10:47:30.000' AS DateTime))
GO
INSERT [dbo].[DiemDen] ([maDiemDen], [tenDiemDen], [tenTinh]) VALUES (N'DD000001', N'Phú Quốc - Bãi Sao', N'Kiên Giang')
INSERT [dbo].[DiemDen] ([maDiemDen], [tenDiemDen], [tenTinh]) VALUES (N'DD000002', N'Đảo Lý Sơn', N'Đà Nẵng')
INSERT [dbo].[DiemDen] ([maDiemDen], [tenDiemDen], [tenTinh]) VALUES (N'DD000003', N'Nha Trang', N'Khánh Hòa')
GO
INSERT [dbo].[DiemXuatPhat] ([maDiemXuatPhat], [tenDiemXuatPhat], [tenTinh]) VALUES (N'DXP00001', N'Bến xe Cần Thơ', N'Cần Thơ')
INSERT [dbo].[DiemXuatPhat] ([maDiemXuatPhat], [tenDiemXuatPhat], [tenTinh]) VALUES (N'DXP00002', N'Bến xe Miền Đông', N'Hồ Chí Minh')
INSERT [dbo].[DiemXuatPhat] ([maDiemXuatPhat], [tenDiemXuatPhat], [tenTinh]) VALUES (N'DXP00003', N'Bến xe phía Bắc thành phố Nha Trang', N'Khánh Hòa')
GO
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000001', N'Nguyễn Phan Hoàng', N'Long', 0, CAST(N'1978-11-09' AS Date), N'782225963', N'0996328544', N'wcoonan1e@harvard.edu')
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000002', N'Trần Thị Huyền', N'Trang', 1, CAST(N'1979-12-20' AS Date), N'761255214', N'0338571963', N'mcunnah1r@earthlink.net')
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000003', N'Võ Trần Tuấn', N'Anh', 0, CAST(N'2000-01-01' AS Date), N'012996387', N'0712685563', N'bdavidsson20@eepurl.com')
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000004', N'Nguyễn Phương', N'Anh', 1, CAST(N'1996-05-26' AS Date), N'522910447', N'0886330011', N'hlukash1s@msu.edu')
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000005', N'Phan Van', N'Phúc', 0, CAST(N'2001-02-26' AS Date), N'225852321', N'0809652212', NULL)
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000006', N'Hoàng Anh', N'Quốc', 0, CAST(N'1989-09-12' AS Date), N'079256941296', N'0788884120', N'fbody1n@joomla.org')
INSERT [dbo].[KhachHang] ([maKhachHang], [hoKhachHang], [tenKhachHang], [gioiTinh], [ngaySinh], [soCMND], [soDienThoai], [email]) VALUES (N'KH000007', N'Bùi Quang', N'Huy', 0, CAST(N'1990-04-30' AS Date), N'226985412', N'0987112321', NULL)
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [ngaySinh], [gioiTinh], [cmnd], [soDienThoai], [email]) VALUES (N'NV000001', N'Võ Đức Toàn', CAST(N'2001-02-04' AS Date), 0, N'226685212', N'0365221452', N'ehuffy1u@army.mil')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [ngaySinh], [gioiTinh], [cmnd], [soDienThoai], [email]) VALUES (N'NV000002', N'Phan Hồng Hà', CAST(N'2001-02-03' AS Date), 0, N'226741216', N'0998522156', N'showsden21@icio.us')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [ngaySinh], [gioiTinh], [cmnd], [soDienThoai], [email]) VALUES (N'NV000003', N'Nguyễn Hoàn Hữu', CAST(N'2001-09-30' AS Date), 0, N'226741232', N'0891111369', N'nfowles22@google.pl')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [ngaySinh], [gioiTinh], [cmnd], [soDienThoai], [email]) VALUES (N'NV000004', N'Phan Đình Nhật', CAST(N'2001-08-10' AS Date), 0, N'224222352', N'0388852110', N'mantonich1w@weather.com')
GO
ALTER TABLE [dbo].[ChuyenDi]  WITH CHECK ADD FOREIGN KEY([maDiemXuatPhat])
REFERENCES [dbo].[DiemXuatPhat] ([maDiemXuatPhat])
GO
ALTER TABLE [dbo].[ChuyenDi]  WITH CHECK ADD FOREIGN KEY([maDiemDen])
REFERENCES [dbo].[DiemDen] ([maDiemDen])
GO
ALTER TABLE [dbo].[DatVe]  WITH CHECK ADD FOREIGN KEY([maChuyenDi])
REFERENCES [dbo].[ChuyenDi] ([maChuyenDi])
GO
ALTER TABLE [dbo].[DatVe]  WITH CHECK ADD FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[DatVe]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
