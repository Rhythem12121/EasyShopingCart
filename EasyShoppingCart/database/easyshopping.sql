-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2015 at 04:22 AM
-- Server version: 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `easyshopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE IF NOT EXISTS `article` (
`article_id` int(10) NOT NULL,
  `customer_id` int(10) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `price` float NOT NULL,
  `phonenumber` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
`CartId` int(20) NOT NULL,
  `CustomerId` int(20) DEFAULT NULL,
  `Description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`CartId`, `CustomerId`, `Description`, `CreateTime`) VALUES
(1, 1, 'Chưa có', '2015-10-20 15:15:41'),
(2, 1, 'Chưa có', '2015-10-20 15:15:41'),
(3, 1, 'bla', '2015-12-11 09:06:49'),
(4, 3, 'bla', '2015-12-11 09:06:55'),
(5, 3, 'bla', '2015-12-11 09:08:19'),
(6, 3, 'bla', '2015-12-11 09:11:08'),
(7, 3, 'bla', '2015-12-11 09:14:30'),
(8, 3, 'bla', '2015-12-11 09:14:54'),
(9, 1, 'khÃ´ng rÃµ', '2015-12-15 16:41:36'),
(10, 1, 'khÃ´ng rÃµ', '2015-12-18 16:16:11'),
(11, 1, 'khÃ´ng rÃµ', '2015-12-18 16:17:57');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
`CategoriesId` int(20) NOT NULL,
  `Title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` text COLLATE utf8_unicode_ci
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`CategoriesId`, `Title`, `Description`) VALUES
(1, 'Laptop', 'description'),
(2, 'Smartphone', 'description'),
(3, 'Camera', 'description'),
(4, 'orther', 'description');

-- --------------------------------------------------------

--
-- Table structure for table `crules`
--

CREATE TABLE IF NOT EXISTS `crules` (
`crule_id` int(11) NOT NULL,
  `subsequent` text COLLATE utf8_unicode_ci NOT NULL,
  `condition` text COLLATE utf8_unicode_ci NOT NULL,
  `confidence` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `crules`
--

INSERT INTO `crules` (`crule_id`, `subsequent`, `condition`, `confidence`) VALUES
(1, '4 -1 5 6 -1', '4 -1 5 -1', 1),
(2, '2 3 -1 5 6 -1', '2 3 -1 6 -1', 1),
(3, '2 3 -1 5 6 -1', '2 -1 6', 1),
(4, '2 3 4 -1', '2 4 -1', 1),
(5, '2 3 4 -1', '3 4 -1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `csequences`
--

CREATE TABLE IF NOT EXISTS `csequences` (
`csequence_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `data` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `csequences`
--

INSERT INTO `csequences` (`csequence_id`, `customer_id`, `data`) VALUES
(1, 1, '1 2 -1 2 3 4 -1 -2'),
(2, 2, '4 5 6 -1 1 2 -1 3 2 -1 -2'),
(3, 3, '1 6 -1 -2'),
(4, 4, '1 2 3 4 -1 2 3 -1 -2');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
`CustomerId` int(20) NOT NULL,
  `FullName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UserName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Birthday` datetime DEFAULT NULL,
  `Email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Gender` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=22 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerId`, `FullName`, `UserName`, `Password`, `Address`, `Birthday`, `Email`, `Gender`) VALUES
(1, 'Nguyễn Tiến Đông', 'dongnt', '123456', 'Hà Đông,Hà Nội', '2015-10-10 00:00:00', 'dongnt@gmail.com', 'nam'),
(2, 'Vũ Văn Tường', 'tritueviet', '123456', 'Hà nội', '1993-01-01 00:00:00', 'tuong@gmail.com', 'nam'),
(3, 'Vũ Văn Việt', 'vietvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'viet@gmail.com', 'nam'),
(4, 'Vũ Văn Công', 'congvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'cong@gmail.com', 'nam'),
(5, 'Nguyễn Hoàng Hiệp', 'hiepvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'hiep@gmail.com', 'nam'),
(6, 'Nguyễn Hoàng Cường', 'cuongvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'cuong@gmail.com', 'nam'),
(7, 'Vũ Văn Thượng', 'thuongvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'thuong@gmail.com', 'nam'),
(8, 'Trần Thanh thư', 'thuvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'thu@gmail.com', 'nu'),
(9, 'Trần thị Yến', 'yenvt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'yen@gmail.com', 'nu'),
(10, 'Trần thu Hà', 'havt', '123456', 'Hà nội', '1993-01-01 00:00:00', 'ha@gmail.com', 'nu'),
(11, 'guest11', 'guestname11', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(12, 'guest12', 'guestname12', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(13, 'guest13', 'guestname13', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(14, 'guest14', 'guestname14', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(15, 'guest15', 'guestname15', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(16, 'guest16', 'guestname16', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(17, 'guest17', 'guestname17', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(18, 'guest18', 'guestname18', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(19, 'guest19', 'guestname19', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nam'),
(20, 'guestname20', 'guest20', '123456', 'Hà nội', '1993-01-01 00:00:00', 'guest@gmail.com', 'nu'),
(21, '', '', '', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `detail`
--

CREATE TABLE IF NOT EXISTS `detail` (
`DetailId` int(20) NOT NULL,
  `Price` float DEFAULT NULL,
  `Title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Company` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` text COLLATE utf8_unicode_ci,
  `Infomation` text COLLATE utf8_unicode_ci,
  `Comment` text COLLATE utf8_unicode_ci,
  `Image` text COLLATE utf8_unicode_ci
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `detail`
--

INSERT INTO `detail` (`DetailId`, `Price`, `Title`, `Company`, `Description`, `Infomation`, `Comment`, `Image`) VALUES
(1, 45900, 'MACBOOK PRO RETINA MJLQ2ZP/A', 'Apple', 'Thay đổi lớn nhất trên Macbook Pro Retina 15" MJLQ2ZP/A 2015 đó là tốc độ SSD nhanh hơn 2,5 lần so với đời cũ đạt tốc độ 2GBps (2000MB/s).Thay đổi tiếp theo đó là bàn rê Force Touch mà Apple giới thiệu cách đây không lâu. Về cấu hình, máy được gắn sẵn RAM 16GB và SSD 256GB cùng với VGA theo máy là Intel Iris Pro, không co VGA rời, CPU Core i7 Broadwell Haswell 2.2Ghz. Về ngoại hình cũng như các cổng kết nối thì không có sự khác biệt so với thế hệ trước, chúng ta có 2 cổng USB 3.0, 2 cổng Thunderbolt 2.0, đầu đọc thẻ SD, sạc Magsafe 2 và lỗ cắm tai nghe. Bàn phím cũng là bàn phím chiclet bình thường chứ không phải bàn phím theo kết cầu bươm bướm như ở Macbook 12.', 'Bảo hành: 1 năm\r\nCPU: Core i7, 2.2Ghz\r\nVGA: Intel Iris Pro\r\nRAM: 16GB\r\nBộ nhớ trong: 256GB SSD, PCIe, 2GBps\r\nKích thước màn hình: 15" độ phân giải 2880x1800, 220Ppi\r\nNhập liệu: Chiclet keyboard, Force Touch trackpad\r\nKết nối: khe thẻ SD, HDMI, USB 3.0 x2, Thunderbolt 2.0 x2, Magsafe 2.0, 3.5mm Audio jack\r\nHĐH: MacOS', 'Hiệu năng\r\nMàn hình Macbook Pro Retina 15" MJLQ2ZP/A có độ phân giải 2880x1800 đạt mức 220ppi. Mac OS X có chế độ HiDPI (từ Lion 2011) gom 4 điểm ảnh lại thành 1 điểm ảnh nên chúng ta sẽ nhìn thấy kích thước hình ảnh trên màn hình này tương tự như kích thước 1440x900 nhưng mịn và nét như nhìn trên iPhone Retina. Nếu không có HiDPI thì chắc chắn chúng ta sẽ khó mà xem được nội dung nhỏ xíu trên màn hình.', 'http://img.mediamart.vn/Product/27442_18863_macbook-pro-retina-mjlq2zpa-mid-2015.jpg'),
(2, 29900, 'MACBOOK PRO 13.3" MF839ZP/A', 'Apple', 'MacBook Pro Retina 2015 MF839ZP-A – Force Touch pad thế hệ mới\r\n\r\nVới phiên bản 13 inch của MacBook Pro mới giới thiệu, bạn sẽ được trải nghiệm cảm giác hoàn toàn mới khi thao tác trên Touch Pad của máy. Dùng 4 cảm biến Force mới đặt ở 4 góc. Các trackpad thông thường sẽ rất khó để nhận được các thao tác bấm ở các vị trí gần bàn phím. Các cảm biến Force sẽ nhận diện bạn chạm vào đâu trên bề mặt trackpad và “di chuyển nhẹ” trackpad về phía chúng ta để nhận diện chính xác hơn. Ngoài ra, Trackpad mới có cảm biến rung (taptic) rung phản hồi lại khi chúng ta chạm, giống với kiểu điện thoại.\r\nChưa dừng lại ở đó, bàn di chuột này còn có thêm tính năng cảm nhận lực nhấn của người dùng. Bạn có thể thêm các thao tác nhấn mạnh hay nhấn yếu để thực hiện những mệnh lệnh tương ứng. Bàn di này đồng thời nhận được ngón tay nào đang tương tác để thay đổi mức nhận lực tương ứng.', 'CPU Intel, Core i5 Broadwell, 5257U, 2.70 GHz\r\nRAM DDR3L(On board), 8 GB\r\nĐĩa cứng SSD, 128 GB\r\nMàn hình rộng 13.3 inch, Retina (2560 x 1600 pixels)\r\nồ họa Intel HD Graphics 6100, Share\r\nĐĩa quang Không\r\nHĐH theo máy Mac OS X\r\nPIN/Battery Lithium- polymer\r\nTrọng lượng (Kg) 1.58', 'Dể sử dụng, giao diện đẹp và tận dụng hết ưu điểm của phần cứng từ chiếc Mac của bạn là tất cả những gì mô tả về hệ điều hành này. Với sự kết hợp giữa phần cứng mạnh mẽ và phần mềm hiện đại, MacBook Pro sẽ là trợ thủ đắc lực trong công việc hàng ngày của bạn', 'http://img.mediamart.vn/Product/24029_16549_macbook-pro-133-mf839zpa.jpg'),
(3, 32000, 'MACBOOK PRO 13.3" MF840ZP/A', 'Apple', 'Dòng Macbook Pro Retina sẽ mang lại cho bạn những trải nghiệm tuyệt vời trong công việc lẫn giải trí. Không chỉ vậy, nó còn như một món trang sức công nghệ đầy sang trọng và tiện lợi để bạn luôn mang theo bên mình. Trong số ba phiên bản mới 2015, Macbook Pro Retina 13inch SSD 256GB là phiên bản có bộ nhớ ổ cứng lưu trữ SSD trung bình – 256GB, phù hợp cho những người cần một thiết bị để làm việc và giải trí nhẹ nhàng, nhưng vẫn trải nghiệm được những điều tuyệt vời mà một chiếc Macbook sẽ mang lại. ', 'CPU Intel, Core i5 Broadwell, 5257U, 2.70 GHz\r\nRAM DDR3L(On board), 8 GB\r\nĐĩa cứng SSD, 256 GB\r\nMàn hình rộng 13.3 inch, Retina (2560 x 1600 pixels)\r\nCảm ứng Không\r\nĐồ họa Intel HD Graphics 6100, Share\r\nĐĩa quang Không\r\nPIN/BatteryLithium- polymer\r\nHĐH Mac OS', 'Thiết kế đặc trưng từ APPLE\r\nSo với các phiên bản trước đó Apple Macbook Pro 2015 MF840ZP/A i5 5257U/8GB/256GB hay gọi tắt là Macbook Pro 2015 không có sự thay đổi khác biệt. Máy vẫn được trang bị lớp vỏ nhôm nguyên khối Unibody rất đẹp và chắn chắn. Thiết kế mỏng, nhẹ ấn tượng, chỉ mỏng 1.8cm và trọng lượng là 1,58 kg, rất tiện lợi khi di chuyển. ', 'http://mediamart.vn/Images/Upload/download/2015-7/mf840zpa_26d4467f-2d54-4155-bc69-41dac374cdaf.png'),
(4, 31000, 'MACBOOK PRO 13.3" MF841ZP/A', 'Apple', 'Dòng Macbook Pro Retina sẽ mang lại cho bạn những trải nghiệm tuyệt vời trong công việc lẫn giải trí.  Không chỉ vậy, nó còn như một món trang sức công nghệ đầy sang trọng và tiện lợi để bạn luôn mang theo bên mình. Trong số ba phiên bản mới 2015, Macbook Pro 13.3 inch SSD 512GB là phiên bản có bộ nhớ ổ cứng lưu trữ SSD cao nhất – 512GB, phù hợp cho những người cần một thiết bị để làm việc và giải trí nhẹ nhàng, nhưng vẫn trải nghiệm được những điều tuyệt vời mà một chiếc Macbook sẽ mang lại. ', 'CPU Intel, Core i5 Broadwell, 5257U, 2.70 GHz\r\nRAM DDR3L(On board), 8 GB\r\nĐĩa cứng SSD, 256 GB\r\nMàn hình rộng 13.3 inch, Retina (2560 x 1600 pixels)\r\nCảm ứng Không\r\nĐồ họa Intel HD Graphics 6100, Share\r\nĐĩa quang Không\r\nPIN/BatteryLithium- polymer\r\nHĐH Mac OS', 'Thiết kế đặc trưng từ APPLE\r\nSo với các phiên bản trước đó Apple Macbook Pro 2015 MF840ZP/A i5 5257U/8GB/256GB hay gọi tắt là Macbook Pro 2015 không có sự thay đổi khác biệt. Máy vẫn được trang bị lớp vỏ nhôm nguyên khối Unibody rất đẹp và chắn chắn. Thiết kế mỏng, nhẹ ấn tượng, chỉ mỏng 1.8cm và trọng lượng là 1,58 kg, rất tiện lợi khi di chuyển. ', 'http://img.mediamart.vn/Product/26618_18469_macbook-pro-133-mf841zpa.jpg'),
(5, 23900, 'MACBOOK PRO RETINA 13" MD101', 'Apple', 'Dòng Macbook Pro Retina sẽ mang lại cho bạn những trải nghiệm tuyệt vời trong công việc lẫn giải trí. Không chỉ vậy, nó còn như một món trang sức công nghệ đầy sang trọng và tiện lợi để bạn luôn mang theo bên mình. Trong số ba phiên bản mới 2015, Macbook Pro 13.3 inch SSD 512GB là phiên bản có bộ nhớ ổ cứng lưu trữ SSD cao nhất – 512GB, phù hợp cho những người cần một thiết bị để làm việc và giải trí nhẹ nhàng, nhưng vẫn trải nghiệm được những điều tuyệt vời mà một chiếc Macbook sẽ mang lại.  ', 'Bảo hành: 1 năm\r\nBộ xử lý: Intel Core i5 - 2.5Ghz\r\nRAM: 4GB - DDR3 - 1600Mhz\r\nHDD: SATA - 500GB - 5400rpm\r\nĐồ họa: Intel HD graphics 4000\r\nHệ điều hành hỗ trợ: Mac OS\r\nThời gian bảo hành: 12 Tháng', 'Thiết kế đặc trưng từ APPLE\r\nSo với các phiên bản trước đó Apple Macbook Pro 2015 MF840ZP/A i5 5257U/8GB/256GB hay gọi tắt là Macbook Pro 2015 không có sự thay đổi khác biệt. Máy vẫn được trang bị lớp vỏ nhôm nguyên khối Unibody rất đẹp và chắn chắn. Thiết kế mỏng, nhẹ ấn tượng, chỉ mỏng 1.8cm và trọng lượng là 1,58 kg, rất tiện lợi khi di chuyển. ', 'http://img.mediamart.vn/Product/21702_15501_macbook-pro-retina-13-md101zpa.jpg'),
(6, 24890, 'APPLE IMAC 21.5" MF883ZP/A', 'Apple', 'Chỉ cần ngồi trước iMac mới thì một điều thần kỳ sẽ diễn ra: Thế giới xung quanh bạn gần như biến mất, và bạn sẽ đắm mình trong chiếc màn hình rộng và tuyệt đẹp. Để tạo ra một trải nghiệm trung thực đến vậy, Apple đã phải thách thức mọi giới hạn, thiết kế lại từng chi tiết và nâng cấp iMac theo những cách tuyệt nhất.', 'CPU: 1.4GHz Dual-core Intel Core i5\r\nRam: 8GB of 1600MHz LPDDR3\r\nGPU: Intel HD Graphics 5000\r\nHDD: 500GB (5400-rpm) hard drive\r\nMàn hình: 21.5" 1920 x 1080', 'Tiết kiệm năng lượng: Không chỉ có hiệu năng cao hơn bất kỳ iMac đời trước nào, chiếc iMac mới còn tiết kiệm năng lượng hơn. Nó sử dụng ít điện năng hơn tới 50% ở chế độ tĩnh với màh hình bật. Ngay cả phần cứng của máy cũng kết hợp với phần mềm giúp tiết kiệm thêm điện năng. Thân thiện với môi trường: iMac được thiết kế hoàn toàn không sử dụng bất kỳ chất độc nào, trong đó có thủy ngân, arsen, BFR và PVC. Nó còn làm từ những vật liệu như nhôm và kiếng, những vật liệu dễ tái chế hơn. Tiêu chuẩn danh giá: Từ những nỗ lực của Apple, iMac đạt được những tiêu chuẩn tiết kiệm điện của EPA, giúp nó đạt được chuẩn ENERGY STAR 5.2. Và nó cũng đạt được điểm cao nhất - chuẩn vàng của EPEAT, chuẩn đánh giá mức độ thân thiện của một sản phẩm tới môi trường thông qua khả năng bảo vệ môi trường, tiết kiệm năng lượng, quá trình thiết kế cũng như sản xuất.', 'http://img.mediamart.vn/Product/18925_14202_may-tinh-ban-apple-imac-mf883.jpg'),
(7, 32400, 'APPLE IMAC 21.5 INCH ME086ZP/A', 'Apple', 'ME086 là chiếc máy tính all in one hiện đại nhất được thiết kế với đồ họa nhanh hơn và cấu trúc vi xử lý mới. Máy Mac cho bạn hiệu quả tốt hơn trong những việc bạn hay làm như: truy cập Internet, lướt web, kiểm tra thư điện tử và làm việc với các tập tin Microsoft Office (Ms Word, Ms Excel, Power Point…). Bạn có thể làm mọi thứ tuyệt vời hơn với ảnh, phim và âm nhạc. Hơn nữa với hệ điều hành Mac OS Snow Leopard sẽ giúp bạn đạt được những điều tốt nhất từ máy Mac.', 'Màn hình 21.5 inch, độ phân giải 1920x1080\r\nBộ xử lý 2.7GHz quad-core Intel Core i5 (Turbo Boost up to 3.2GHz), 4MB L3 Cache\r\nRAM 8GB (2x4) 1600MHz DDR3\r\nỔ cứng 1TB (5400 rpm) HDD\r\nCard đồ họa Intel Iris Pro graphics 5200\r\nCamera Facetime HD Camera\r\nHĐH Mac OS', 'Mac - ME086 sử dụng ít điện năng hơn tới 50% ở chế độ tĩnh với màh hình bật. Ngay cả phần cứng của máy cũng kết hợp với phần mềm giúp tiết kiệm thêm điện năng.\r\nChiếc máy tính all in one này được thiết kế hoàn toàn không sử dụng bất kỳ chất độc nào, trong đó có thủy ngân, arsen, BFR và PVC. Nó còn làm từ những vật liệu như nhôm và kiếng, những vật liệu dễ tái chế hơn.\r\nTừ những nỗ lực của Apple, ME086 đạt được những tiêu chuẩn tiết kiệm điện của EPA, giúp nó đạt được chuẩn ENERGY STAR 5.2. Và nó cũng đạt được điểm cao nhất - chuẩn vàng của EPEAT, chuẩn đánh giá mức độ thân thiện của một sản phẩm tới môi trường thông qua khả năng bảo vệ môi trường, tiết kiệm năng lượng, quá trình thiết kế cũng như sản xuất.', 'http://mediamart.vn/Images/Upload/download/2015-7/me086_26d5e168-6694-4777-8b76-60cdc8c2525f.png'),
(8, 15380, 'APPLE IPHONE 6 16GB SILVER', 'Apple', 'Thiết kế hoàn toàn mới\r\nKhung nhôm nguyên khối cao cấp rất mỏng, nhẹ (129 gram), dày 6.9 mm thuộc nhóm điện thoại mỏng nhất thế giới.\r\nCamera iSight 8MP\r\niPhone 6 có cảm biến iSight mới và nhiều tính năng hiệu chỉnh chụp ảnh bằng tay.\r\nWifi nhanh hơn\r\nWifi chuẩn AC có tốc độ kết nối internet tăng gấp 3 lần và có khả năng thanh toán trực tuyến.\r\nTích hợp NFC\r\nApple cũng giới thiệu dịch vụ thanh toán di động Apple Pay để tận dụng ưu điểm của công nghệ này\r\n', 'Hệ điều hành: iOS 8 mới nhất\r\nBộ xử lý : Chipset Apple A8 64bit mới nhất\r\nMàn hình: 4.7 inch Retina HD\r\nCamera với cảm biến đời mới iSight: 8MP\r\nCông nghệ Focus Pixels giúp lấy nét nhanh và chính xác hơn\r\nChụp liên tục 10 ảnh/giây\r\nBộ nhớ trong: 16GB\r\nNFC/ 3G/ Wi-fi/ Bluetooth/ GPS/ Nano-Sim', 'Ở iPhone 6 Plus với màn hình lớn 5,5" thì chúng ta có thêm giao diện xoay ngang, tối ưu những nội dung hiển thị theo chiều ngang. Giả sử như bạn truy cập vào ứng dụng Mail thì một bên sẽ là danh sách email, một bên sẽ là phần hiển thị nội dung, tương tự như tablet. Camera Apple không nâng cấp độ phân giải 8MP của camera mà chỉ thay đổi các thành phần để mang đến chấn lượng tốt hơn. Chúng ta có kích thước của điểm ảnh trên cảm biến hoàn toàn mới là 1,5µm và khẩu mở ở F2.2, so với iPhone 5s trước đó là 1,4µm và khẩu độ F2.4. Điều này cho thấy khả năng hấp thụ ánh sáng của cảm biến sẽ tốt hơn. Cảm biến này còn sở hữu tính năng lấy nét theo pha mà Apple gọi là Focus Pixels, đem lại khả năng nhận diện khoảng cách chủ thể và bối cảnh tốt hơn, nhờ đó mà khả năng lấy nét sẽ được cải thiện hơn. Camera của iPhone 6 Plus sẽ lồi lên khỏi mặt phẳng của máy bởi nó phải tích hợp một hệ thống ổn định hình ảnh quang học thế hệ mới. Thông thường tính năng OIS trên các dòng điện thoại hiện thời chỉ di chuyển cụm ống kính theo chiều ngang, còn Apple kết hợp thêm khả năng ổn định hình ảnh theo chiều dọc, tối ưu luôn việc lấy nét với Focus Pixels.', 'http://mediamart.vn/Images/Upload/download/2014-9/iphone-6_a9eb8ee3-cc87-418f-898c-713b90608448.png'),
(9, 6500000, 'APPLE IPHONE 5S 16GB CHAMPAGNE GOLD', 'Apple', 'Thiết kế\r\nTôn chỉ thiết kế của iPhone 5S tiếp tục được duy trì từ dòng iPhone truyền thống chứ không “phá cách” như chiếc iPhone 5C. Nói một câu chung nhất thì 5S có thiết kế gần như giống hệt với dòng iPhone 5.\r\nMàn hình\r\n\r\nNgười dùng iPhone luôn thấy “chạnh lòng” vì thiết bị của mình có màn hình “tí hon” nếu so với những con khủng long của thế giới Android (hay thậm chí là Windows Phone). Tuy nhiên, việc iPhone 5S chưa ra tăng kích cỡ cũng có cái lý của nó: liệu người dùng có còn thoải mái, có thể sử dụng iPhone bằng 1 tay hay không khi gia tăng kích cỡ, dẫn đến chiều rộng màn hình lớn hơn? Câu trả lời có lẽ là không. Vả chăng, thông tin về một thiết bị phablet của Apple đang bắt đầu có mặt trên các kênh không chính thức. Nếu đã có một sản phẩm như vậy, lý gì Apple lại phải tăng kích cỡ iPhone cho tốn kém và phức tạp trong chế tạo?', 'Hệ điều hành iOS 7 mới nhất\r\nThiết kế siêu mỏng 7.6mm\r\nNút Home cảm ứng bảo mật vân tay cao cấp\r\nBộ xử lý Apple A7 cấu trúc 64 bit\r\nChip xử lý chuyển động M7 riêng biệt\r\nMàn hình: 4.0 inches (640 x 1136 px)\r\nBộ nhớ trong: 16 GB\r\nCamera 8 MP chụp hình đẹp hơn iPhone 5\r\nQuay phim Full HD, chụp ảnh trong khi quay\r\nCellular/3G/ Wi-Fi/ Bluetooth/ GPS', 'Sau bao chờ đợi mòn mỏi, chúng ta đã được chứng kiến phiên bản iPhone mới nhất, mạnh mẽ nhất từng được Apple chế tạo.\r\n\r\niPhone 5S là chiếc điện thoại được mong chờ nhất trong thời gian vừa qua, đó là điều không cần bàn cãi. Search trên Google với từ khóa “iPhone 5S”, ta sẽ có được tới gần 600 triệu kết quả, một kết quả đáng “mơ ước” của bất kỳ chiếc điện thoại nào.\r\n\r\nChúng ta có lẽ không mong chờ nhiều điều cải tiến mang tính đột phá ở iPhone 5S, vì phiên bản có chữ “S” thường không có nhiều sự thay đổi so với phiên bản tiền nhiệm mà chỉ tập trung cải thiện trải nghiệm người dùng. Điều này đã được chứng minh khi 3GS đơn giản là tăng tốc độ xử lý, còn 4S thì thêm vào người “trợ lý” Siri so với các phiên bản trước của chúng. Tuy nhiên, với iPhone 5S, Apple đã quyết tâm lấy lại vị thế một trong những công ty sáng tạo nhất thế giới bằng cách trang bị cho nó một loạt cải tiến, chưa hẳn mang tính cách mạng, nhưng cũng là rất đáng kể.', 'http://img.mediamart.vn/Product/531f605d-4cd4-4037-8490-ea9e7833332b_apple-iphone-5s-champagne-gold.jpg'),
(10, 230000, 'PIN DỰ TRỮ IPHONE, IPOD', 'Cayon', 'Chưa có', 'Chưa có', 'Chưa có', 'http://img.mediamart.vn/Product/29133_pin-du-tru-iphone-ipod.jpg'),
(11, 250000, 'TAI NGHE BLUETOOTH IPHONE 4S', 'Apple', 'Tai nghe/ Loa DĐ Tai nghe Bluetooth Iphone 4S', 'Sử dụng cho tất cả các điện thoại có bluetooth! (chuyên dụng cho iPhone 4)\r\nÂm thanh Mono\r\nLọc tiếng ồn cực tốt! Âm thanh to, rõ \r\nKhoảng cách thu phát sóng :10m\r\nThời gian thoại : 8 giờ\r\nThời gian chờ : 300 giờ \r\nThiết kế sành điệu! Mỏng, nhẹ!\r\nPin siêu bền', 'Chưa có', 'http://img.mediamart.vn/Product/9607_tai-nghe-bluetooth-iphone-4s.jpg'),
(12, 1490000, 'MÁY NGHE NHẠC IPOD SHUFFLE NEW 2GB', 'Apple', 'Chưa có', 'Dung lượng bộ nhớ trong 2GB\r\nHỗ trợ định dạng file âm thanh: AAC, MP3, MP3 VBR, Apple Lossless, AIFF, WAV\r\nThời nghe nhạc lên đến 15 giờ (Pin sạc đầy)\r\nCó thể sạc pin qua cổng USB\r\nCắm tai nghe hoặc sạc pin qua jack cắm 3.5mm\r\nKích thước (HxWxD): 29 x 31.6 x 8.7 mm\r\nTrọng lượng: 12.5g\r\nPhụ kiện: Tai nghe, Cáp USB\r\nCó 5 màu: bạc, xanh dương, xanh lá, cam, hồng', 'Chưa có', 'http://img.mediamart.vn/Product/12133_may-nghe-nhac-ipod-shuffle-new-2gb.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
`OrderId` int(20) NOT NULL,
  `CartId` int(20) DEFAULT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Card` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Price` float DEFAULT NULL,
  `CreateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=6 ;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`OrderId`, `CartId`, `Address`, `Card`, `Status`, `Price`, `CreateTime`) VALUES
(1, 7, 'Hà nội', 'Visa', 'pending', 1000, '2015-12-11 09:47:45'),
(2, 7, 'Hanoi', 'visa', 'pending', 1000, '2015-12-11 10:16:24'),
(3, 9, 'HÃ  Ná»™i', 'Visa', 'K', 1234, '2015-12-15 16:41:39'),
(4, 10, 'Ha Noi', 'Visa', 'Khong', 12356, '2015-12-18 16:16:13'),
(5, 11, 'Ha Noi', 'Visa', 'Khong', 12356, '2015-12-18 16:17:59');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
`ProductId` int(20) NOT NULL,
  `CategoriesId` int(20) DEFAULT NULL,
  `ProductDetailId` int(20) DEFAULT NULL,
  `Quanlity` int(20) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductId`, `CategoriesId`, `ProductDetailId`, `Quanlity`) VALUES
(1, 1, 1, 20),
(2, 1, 2, 20),
(3, 1, 3, 20),
(4, 1, 4, 20),
(5, 1, 5, 20),
(6, 1, 6, 20),
(7, 1, 7, 20),
(8, 2, 8, 20),
(9, 2, 9, 20),
(10, 4, 10, 20),
(11, 4, 11, 20),
(12, 4, 12, 20);

-- --------------------------------------------------------

--
-- Table structure for table `productcartdetail`
--

CREATE TABLE IF NOT EXISTS `productcartdetail` (
`ProductCartDetailId` int(20) NOT NULL,
  `CartId` int(20) DEFAULT NULL,
  `ProductId` int(20) DEFAULT NULL,
  `Quanlity` int(20) DEFAULT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `productcartdetail`
--

INSERT INTO `productcartdetail` (`ProductCartDetailId`, `CartId`, `ProductId`, `Quanlity`) VALUES
(1, 1, 1, 1),
(2, 1, 11, 1),
(3, 7, 1, 1),
(4, 9, 9, 20),
(5, 10, 2, 20),
(6, 11, 2, 20);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
 ADD PRIMARY KEY (`article_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
 ADD PRIMARY KEY (`CartId`), ADD KEY `fk_cart_customer_idx` (`CustomerId`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
 ADD PRIMARY KEY (`CategoriesId`);

--
-- Indexes for table `crules`
--
ALTER TABLE `crules`
 ADD PRIMARY KEY (`crule_id`);

--
-- Indexes for table `csequences`
--
ALTER TABLE `csequences`
 ADD PRIMARY KEY (`csequence_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
 ADD PRIMARY KEY (`CustomerId`);

--
-- Indexes for table `detail`
--
ALTER TABLE `detail`
 ADD PRIMARY KEY (`DetailId`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
 ADD PRIMARY KEY (`OrderId`), ADD KEY `fk_order_cart_idx` (`CartId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
 ADD PRIMARY KEY (`ProductId`), ADD KEY `fk_product_categories_idx` (`CategoriesId`), ADD KEY `fk_product_detail_idx` (`ProductDetailId`);

--
-- Indexes for table `productcartdetail`
--
ALTER TABLE `productcartdetail`
 ADD PRIMARY KEY (`ProductCartDetailId`), ADD KEY `fk_productcartdetail_cart_idx` (`CartId`), ADD KEY `fk_productcartdetail_product_idx` (`ProductId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
MODIFY `article_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
MODIFY `CartId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
MODIFY `CategoriesId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `crules`
--
ALTER TABLE `crules`
MODIFY `crule_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `csequences`
--
ALTER TABLE `csequences`
MODIFY `csequence_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
MODIFY `CustomerId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `detail`
--
ALTER TABLE `detail`
MODIFY `DetailId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
MODIFY `OrderId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
MODIFY `ProductId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `productcartdetail`
--
ALTER TABLE `productcartdetail`
MODIFY `ProductCartDetailId` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
