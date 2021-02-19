-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: mindwiki
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

use mindwiki;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `achievements`
--

LOCK TABLES `achievements` WRITE;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bookshelf`
--

LOCK TABLES `bookshelf` WRITE;
/*!40000 ALTER TABLE `bookshelf` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookshelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (7,148,'님 티어가?\n','kjw11036@naver.com','2021-02-18 12:41:42'),(8,148,'저 골드 서폿터인데 같이 듀오하실래요?','kjw11036@naver.com','2021-02-18 12:41:58'),(9,149,'면접 준비 하실분~~~','kjw11036@naver.com','2021-02-18 12:43:54'),(10,149,'저요저요','kjw11036@naver.com','2021-02-18 13:57:36'),(11,149,'하하호호','kjw11036@naver.com','2021-02-18 13:57:43'),(12,149,'안녕하세요 kjw님 !!!','mindwiki.yoonho@naver.com','2021-02-18 13:58:53'),(13,149,'저 팔로우 해주세요~~~ 그리고 코테 준비도 넣어주세요','mindwiki.yoonho@naver.com','2021-02-18 13:59:20'),(14,149,'재밌네요','mindwiki.yoonho@naver.com','2021-02-18 14:09:32'),(15,155,'주짓수 같이 배워요~~~~오쓰','ssafy4th@naver.com','2021-02-18 15:43:49'),(16,156,'코테 준비도 같이해요!!!','mindwiki.yoonho@naver.com','2021-02-18 15:45:31'),(17,156,'코테준비 좋아요~','ssafy4th@naver.com','2021-02-18 15:46:28'),(18,156,'팔로우 해주세요 연락해요~~','mindwiki.yoonho@naver.com','2021-02-18 16:57:06'),(19,166,'멍멍','ssafy.admin@naver.com','2021-02-18 18:11:49'),(20,167,'저도 싸피 교육과정 듣고 싶어요!!!','ssafy4th@naver.com','2021-02-18 18:15:40'),(21,142,'주식인줄알았어요 ㅋㅋㅋ','giga111435@gmail.com','2021-02-18 19:19:16'),(22,189,'흥민형은 월클이지','temp01@naver.com','2021-02-19 01:11:08'),(23,189,'월클~~~','temp02@naver.com','2021-02-19 01:11:42'),(24,189,'끼야~\n','temp03@naver.com','2021-02-19 01:13:53'),(25,167,'대박 나도 싸피할래!','temp05@naver.com','2021-02-19 01:15:23'),(26,167,'싸피 짱','temp06@naver.com','2021-02-19 01:15:53'),(27,167,'대박!','kjw11036@naver.com','2021-02-19 01:37:11'),(28,187,'안녕하세요 ;)','giga111435@gmail.com','2021-02-19 04:46:50'),(29,214,'안녕하세요','giga111435@gmail.com','2021-02-19 04:55:45'),(30,219,'ㅁㄴㅇㅁㄴㅇ','tlsdlf5@gmail.com','2021-02-19 05:29:03'),(31,219,'ㅁㄴㅇㅁㄴㅇㅁㄴ','tlsdlf5@gmail.com','2021-02-19 05:29:07'),(32,219,'ㄴㅁㅇㅁㄴㅇ','tlsdlf5@gmail.com','2021-02-19 05:29:09'),(33,219,'오호 신일님이신가용\n','kjw11036@naver.com','2021-02-19 05:49:42'),(34,219,'꺄 반가워요\n','kjw11036@naver.com','2021-02-19 05:53:47'),(35,219,'프로필>마인드맵선택해서 수정하시면.. 이미지 캡처됩니닷?','giga111435@gmail.com','2021-02-19 05:54:24'),(36,224,'고생하셨습니다 석박지 선장','lblbjy@gmail.com','2021-02-19 07:03:27');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `connectionlist`
--

LOCK TABLES `connectionlist` WRITE;
/*!40000 ALTER TABLE `connectionlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `connectionlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cover`
--

LOCK TABLES `cover` WRITE;
/*!40000 ALTER TABLE `cover` DISABLE KEYS */;
/*!40000 ALTER TABLE `cover` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `expert`
--

LOCK TABLES `expert` WRITE;
/*!40000 ALTER TABLE `expert` DISABLE KEYS */;
/*!40000 ALTER TABLE `expert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `followerlist`
--

LOCK TABLES `followerlist` WRITE;
/*!40000 ALTER TABLE `followerlist` DISABLE KEYS */;
INSERT INTO `followerlist` VALUES ('mindwiki.yoonho@naver.com','kjw11036@naver.com','SSK'),('mindwiki.yoonho@naver.com','ssafy4th@naver.com','진짜백냥'),('ssafy4th@naver.com','mindwiki.yoonho@naver.com','노원구 페이커'),('ybj0749@gmail.com','temp17@naver.com','뉴턴'),('ybj0749@gmail.com','temp16@naver.com','삼성전자 채용'),('ybj0749@gmail.com','temp13@naver.com','Black Belt'),('ybj0749@gmail.com','giga111435@gmail.com','웅앵웅아'),('ybj0749@gmail.com','temp11@naver.com','문화체육관광부'),('ybj0749@gmail.com','omt1025@gmail.com','오민택'),('ybj0749@gmail.com','asdf@naver.com','김동욱'),('temp17@naver.com','asdf@naver.com','김동욱'),('ybj0749@gmail.com','mindwiki.manager@gmail.com','Wiki Mind'),('omt1025@gmail.com','mindwiki.manager@gmail.com','Wiki Mind'),('temp17@naver.com','mindwiki.manager@gmail.com','Wiki Mind'),('omt1025@gmail.com','asdf@naver.com','김동욱'),('temp17@naver.com','mindwiki.manager@gmail.com','Wiki Mind'),('mindwiki.yoonho@naver.com','mindwiki.manager@gmail.com','Wiki Mind'),('mindwiki.yoonho@naver.com','temp13@naver.com','Black Belt'),('mindwiki.yoonho@naver.com','ybj0749@gmail.com','신충현'),('giga111435@gmail.com','joodk1992@gmail.com','주동'),('giga111435@gmail.com','mindwiki.yoonho@naver.com','노원구 페이커'),('kjw11036@naver.com','tlsdlf5@gmail.com','쥐대장');
/*!40000 ALTER TABLE `followerlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hashtaglist`
--

LOCK TABLES `hashtaglist` WRITE;
/*!40000 ALTER TABLE `hashtaglist` DISABLE KEYS */;
INSERT INTO `hashtaglist` VALUES (142,'가드'),(142,'스윕'),(142,'패스'),(150,'여행지'),(150,'음식'),(150,'SNOW'),(154,'Movavi'),(154,'UCC'),(154,'B급 감성'),(155,'기본 원리'),(155,'기술'),(155,'포지션'),(157,'LOL'),(157,'FAKER'),(157,'Movavi'),(158,'T1'),(158,'DK'),(158,'FPX'),(161,'면접'),(161,'시험'),(161,'자소서'),(185,'관성'),(185,'가속도'),(185,'작용반작용'),(187,'물리'),(187,'질량'),(187,'에너지'),(166,'산책'),(166,'먹을거'),(166,'주인'),(218,'여행지'),(218,'음식'),(218,'SNOW'),(219,'여행지'),(219,'음식'),(219,'SNOW'),(221,'여행지'),(221,'음식'),(221,'SNOW'),(222,'여행지'),(222,'음식'),(222,'SNOW'),(223,'여행지'),(223,'음식'),(223,'SNOW'),(224,'여행지'),(224,'음식'),(224,'SNOW');
/*!40000 ALTER TABLE `hashtaglist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `likelist`
--

LOCK TABLES `likelist` WRITE;
/*!40000 ALTER TABLE `likelist` DISABLE KEYS */;
INSERT INTO `likelist` VALUES ('kjw11036@naver.com',142,'2021-02-18 12:35:17'),('kjw11036@naver.com',148,'2021-02-18 12:38:53'),('kjw11036@naver.com',146,'2021-02-18 12:39:02'),('kjw11036@naver.com',147,'2021-02-18 12:39:03'),('alsxor1025@naver.com',149,'2021-02-18 12:44:17'),('mindwiki.yoonho@naver.com',148,'2021-02-18 12:44:40'),('mindwiki.yoonho@naver.com',148,'2021-02-18 12:44:42'),('mindwiki.yoonho@naver.com',148,'2021-02-18 12:44:42'),('mindwiki.yoonho@naver.com',148,'2021-02-18 12:44:42'),('kjw11036@naver.com',149,'2021-02-18 12:51:15'),('kjw11036@naver.com',150,'2021-02-18 13:14:22'),('mindwiki.yoonho@naver.com',150,'2021-02-18 13:17:10'),('mindwiki.yoonho@naver.com',149,'2021-02-18 13:17:12'),('mindwiki.yoonho@naver.com',146,'2021-02-18 14:09:20'),('ssafy4th@naver.com',155,'2021-02-18 15:43:02'),('temp02@naver.com',160,'2021-02-18 17:23:55'),('temp02@naver.com',160,'2021-02-18 17:23:56'),('temp02@naver.com',159,'2021-02-18 17:24:02'),('temp02@naver.com',156,'2021-02-18 17:24:07'),('temp02@naver.com',152,'2021-02-18 17:24:18'),('temp04@naver.com',164,'2021-02-18 17:57:28'),('temp06@naver.com',164,'2021-02-18 17:59:00'),('temp06@naver.com',166,'2021-02-18 18:01:46'),('mindwiki.yoonho@naver.com',158,'2021-02-18 18:05:51'),('ssafy.admin@naver.com',167,'2021-02-18 18:11:36'),('ssafy.admin@naver.com',167,'2021-02-18 18:11:37'),('ssafy.admin@naver.com',166,'2021-02-18 18:11:40'),('ybj0749@gmail.com',181,'2021-02-18 20:12:44'),('ybj0749@gmail.com',168,'2021-02-18 20:21:40'),('ybj0749@gmail.com',186,'2021-02-19 00:50:38'),('ybj0749@gmail.com',178,'2021-02-19 00:50:42'),('ybj0749@gmail.com',172,'2021-02-19 00:50:45'),('mindwiki.yoonho@naver.com',189,'2021-02-19 01:10:39'),('omt1025@gmail.com',189,'2021-02-19 01:10:44'),('temp01@naver.com',189,'2021-02-19 01:10:51'),('temp02@naver.com',189,'2021-02-19 01:11:30'),('temp05@naver.com',187,'2021-02-19 01:14:27'),('temp05@naver.com',189,'2021-02-19 01:14:39'),('temp05@naver.com',167,'2021-02-19 01:15:15'),('temp06@naver.com',167,'2021-02-19 01:15:57'),('ybj0749@gmail.com',167,'2021-02-19 01:16:37'),('temp06@naver.com',181,'2021-02-19 01:16:44'),('temp06@naver.com',178,'2021-02-19 01:25:07'),('giga111435@gmail.com',187,'2021-02-19 01:29:14'),('giga111435@gmail.com',189,'2021-02-19 01:29:21'),('temp07@naver.com',189,'2021-02-19 01:29:27'),('temp07@naver.com',187,'2021-02-19 01:29:29'),('giga111435@gmail.com',186,'2021-02-19 01:29:30'),('kjw11036@naver.com',167,'2021-02-19 01:37:04'),('kjw11036@naver.com',191,'2021-02-19 01:39:08'),('temp02@naver.com',187,'2021-02-19 01:59:29'),('temp02@naver.com',191,'2021-02-19 01:59:33'),('kjw11036@naver.com',187,'2021-02-19 02:46:37'),('kjw11036@naver.com',199,'2021-02-19 03:00:43'),('kjw11036@naver.com',199,'2021-02-19 03:00:44'),('kjw11036@naver.com',199,'2021-02-19 03:00:45'),('mindwiki.manager@gmail.com',199,'2021-02-19 04:29:45'),('kjw11036@naver.com',213,'2021-02-19 04:48:27'),('giga111435@gmail.com',150,'2021-02-19 04:54:44'),('ybj0749@gmail.com',219,'2021-02-19 05:44:14'),('kjw11036@naver.com',219,'2021-02-19 05:49:32'),('ybj0749@gmail.com',222,'2021-02-19 06:04:35'),('lblbjy@gmail.com',224,'2021-02-19 07:02:55');
/*!40000 ALTER TABLE `likelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mind`
--

LOCK TABLES `mind` WRITE;
/*!40000 ALTER TABLE `mind` DISABLE KEYS */;
INSERT INTO `mind` VALUES (142,'kjw11036@naver.com','2021-02-18 12:35:04','주린이 모여라','가드,스윕,패스',NULL,NULL,NULL,NULL,NULL,NULL,NULL,138,0,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191900capture.img','주짓수','주짓수 좋아하는 분들 같이 마인드맵 만들어요~~~'),(146,'giga111435@gmail.com','2021-02-18 12:38:39','짱구','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,1,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218123839스크린샷 2021-02-18 오후 9.37.55.png','주인공','짱구 주인공에 대해 알아볼까요!!'),(149,'kjw11036@naver.com','2021-02-18 12:43:31','취준생 모여라~~~','삼성전자,네이버,카카오',NULL,NULL,NULL,NULL,NULL,NULL,NULL,40,1,NULL,3,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218135544capture.img','대기업 가실분','취준 면접 준비 같이해요'),(150,'mindwiki.yoonho@naver.com','2021-02-18 12:53:37','춥다춥다','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,102,1,NULL,3,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191550capture.img','너무 추워요','겨울 끝나라'),(152,'ssafy4thA204@naver.com','2021-02-18 14:38:49','인공지능 알고리즘 공부할 사람','지도학습,비지도학습,강화학습',NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,1,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218143849thumnail.jpg','인공지능 알고리즘','같이 알고리즘 공부해요~~'),(154,'mindwiki.yoonho@naver.com','2021-02-18 15:05:32','UCC 재밌다','Movavi,UCC,B급 감성',NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,2,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218151236capture.img','UCC 만들기','UCC 만들기 넘 어렵다'),(155,'mindwiki.yoonho@naver.com','2021-02-18 15:15:46','주짓수를 쉽게 이해하기','기본 원리,기술,포지션',NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,1,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218152015capture.img','주짓수 공부','주짓수 공부 마인드맵입니다.'),(156,'ssafy4th@naver.com','2021-02-18 15:41:46','취준할 사람 모여라~','자소서,시험,면접',NULL,NULL,NULL,NULL,NULL,NULL,NULL,45,2,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191833capture.img','취준','같이 취뽀해요~~'),(157,'mindwiki.yoonho@naver.com','2021-02-18 15:52:21','관심 분야','LOL,FAKER,Movavi',NULL,NULL,NULL,NULL,NULL,NULL,NULL,62,1,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191719capture.img','관심분야 정리','참고'),(158,'mindwiki.yoonho@naver.com','2021-02-18 15:53:22','롤 팀정리','T1,DK,FPX',NULL,NULL,NULL,NULL,NULL,NULL,NULL,43,3,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191702capture.img','롤 팀정리','롤 팀 공부'),(159,'temp01@naver.com','2021-02-18 17:14:38','~ Bird ~','새,조류,bird',NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,1,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218171438bird.jpg','새','새 좋아하는 사람'),(160,'temp02@naver.com','2021-02-18 17:21:35','백엔드 전문가 과정','DB,SERVER,AWS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,17,0,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218172135backend.jpg','백엔드','백엔드 공부하쉴?'),(161,'mindwiki.yoonho@naver.com','2021-02-18 17:25:26','취준','면접,시험,자소서',NULL,NULL,NULL,NULL,NULL,NULL,NULL,47,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191643capture.img','취준','취업 준비 하자'),(163,'temp03@naver.com','2021-02-18 17:30:47','프랑스 여행 가이드','안전,패키지,니스',NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,1,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218192428capture.img','프랑스 배낭여행','프랑스 여행 전문 업체입니다. 코로나 상황에도 안전한 여행을 보장합니다. 여행 마인드맵 보시고 연락주세요?'),(165,'temp05@naver.com','2021-02-18 17:38:21','챌린저 갈 사람','롤,듀오,바텀',NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,1,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218173821lol.jpg','롤','lol'),(166,'temp06@naver.com','2021-02-18 18:01:41','멍멍!! 강아지가 좋아하는 것','산책,먹을거,주인',NULL,NULL,NULL,NULL,NULL,NULL,NULL,11,1,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219012631capture.img','강아지','개'),(167,'ssafy.admin@naver.com','2021-02-18 18:04:51','SSAFY 4기 모집 공고','SSAFY,지원,교육',NULL,NULL,NULL,NULL,NULL,NULL,NULL,186,4,NULL,6,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218203358capture.img','SSAFY란?','SSAFY란? 마인드맵 보고 SSAFY 지원하러 고고~~'),(168,'temp07@naver.com','2021-02-18 18:56:14','2020 이슈','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,1,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/202102181856142020 issue.png','이슈','이슈'),(170,'temp08@naver.com','2021-02-18 19:16:03','카카오 채용 프로세스','카카오,채용,개발자',NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218191801capture.img','카카오','채용 프로세스'),(171,'temp10@naver.com','2021-02-18 19:31:00','체리축 알아보기','청축,적축,갈축',NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218193100cherry.jpg','체리축','체리축 입문자를 위한 가이드 입니다'),(172,'temp11@naver.com','2021-02-18 19:35:43','어서와 한국은 처음이지?','서울,부산,제주',NULL,NULL,NULL,NULL,NULL,NULL,NULL,25,0,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218193543한국은 처음이지.jpg','한국 관광','한국 관광을 위한 가이드!'),(173,'temp12@naver.com','2021-02-18 19:38:56','무료 익스텐셩 TOP 10개 ','무료,TOP 10,익스텐션',NULL,NULL,NULL,NULL,NULL,NULL,NULL,25,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218193856nomad nomad.png','익스텐션','노마드 코더님의 무료 익스텐션 TOP 10개 추천 정리했어요'),(176,'temp14@naver.com','2021-02-18 19:47:54','떡상 가즈아~~~','주식,가이드,주식초보',NULL,NULL,NULL,NULL,NULL,NULL,NULL,50,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218194839capture.img','주식초보자','주식'),(178,'temp15@naver.com','2021-02-18 19:53:50','AlphaGo Algorith','강화학습,바둑,인공지능',NULL,NULL,NULL,NULL,NULL,NULL,NULL,25,0,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218195350alphago algorithm.png','AlphaGo','알파고 알고리즘 알아보기'),(179,'temp13@naver.com','2021-02-18 19:56:15','델라히바 스윕 정리!!!','스윕,어드,그립싸움',NULL,NULL,NULL,NULL,NULL,NULL,NULL,80,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218195615주짓수 스윕.png','델라히바','델라히바 스윕 정리했어요'),(181,'temp16@naver.com','2021-02-18 20:03:22','삼성전자 채용 과정입니다. ','신입,경력직,채용',NULL,NULL,NULL,NULL,NULL,NULL,NULL,97,1,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218200322samsung 채용.png','삼성전자','삼성 채용 과정'),(185,'temp17@naver.com','2021-02-18 20:12:29','운동법칙 정리해줄게요!','관성,가속도,작용반작용',NULL,NULL,NULL,NULL,NULL,NULL,NULL,32,3,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218201229뉴턴 법칙.png','운동법칙','이렇게 쉬운걸 모르냐'),(186,'temp18@naver.com','2021-02-18 20:25:37','나쁜 개는 없지...','좋은개,나쁜주인,훈련법',NULL,NULL,NULL,NULL,NULL,NULL,NULL,36,2,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218202537강형욱훈련법.png','개','나쁜 개는 없어요'),(187,'temp20@naver.com','2021-02-18 20:29:16','이걸 왜 몰라','물리,질량,에너지',NULL,NULL,NULL,NULL,NULL,NULL,NULL,58,18,NULL,5,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218235157capture.img','e=mc2','질량 에너지 등가 법칙'),(199,'ybj0749@gmail.com','2021-02-19 02:50:23','프리미어리그','맨유,토트넘,손흥민',NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,1,NULL,4,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219025023premier.png','프리미어리그','프리미어리그'),(218,'giga111435@gmail.com','2021-02-19 05:07:12','ㅎ','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,19,2,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219055320capture.img','ㅗ','ㅗ'),(219,'tlsdlf5@gmail.com','2021-02-19 05:26:45','임시','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,29,2,NULL,2,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219055119capture.img','임시','임시'),(220,'ybj0749@gmail.com','2021-02-19 05:51:56','캡처하고싶다면저장버튼!','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219055156basdfa3rq23artqa213512v.jpg','캡처하고싶다면저장버튼!','캡처하고싶다면저장버튼!'),(221,'mindwiki.yoonho@naver.com','2021-02-19 05:56:25','끝났따','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,12,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219055634capture.img','끝끝','ㅇㅇㅇㅇ'),(222,'giga111435@gmail.com','2021-02-19 05:58:29','끝끝','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,13,0,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219055844capture.img','끼야아앙아','끝'),(223,'zzo1@naver.com','2021-02-19 06:32:38','asdfasdf','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,20,1,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219063304capture.img','asdfasdf','asdfasdf'),(224,'ybj0749@gmail.com','2021-02-19 06:33:50','수정은프로필에서됩니다.','여행지,음식,SNOW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,18,0,NULL,1,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219063352capture.img','수정은프로필에서됩니다.','수정은프로필에서됩니다.'),(225,'lblbjy@gmail.com','2021-02-19 07:09:18','패스해충현아','개인기,탈춤,드리블',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,0,NULL,NULL,NULL,'http://i4a204.p.ssafy.io:8000/mindwiki/image/2021021907091822C5A4C8-D38D-455E-975E-6101A5FEBA5F.jpeg','신충현의 축구','마인드위키 체험');
/*!40000 ALTER TABLE `mind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mindlist`
--

LOCK TABLES `mindlist` WRITE;
/*!40000 ALTER TABLE `mindlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `mindlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `mymindbook`
--

LOCK TABLES `mymindbook` WRITE;
/*!40000 ALTER TABLE `mymindbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `mymindbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `node`
--

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;
INSERT INTO `node` VALUES (31,142,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"가드\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"스윕\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"패스\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"주짓수\\\",\\\"url\\\":\\\"\\\"}]\"'),(35,146,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"주인공\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(38,149,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"삼성전자\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"네이버\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"쿠팡\\\",\\\"children\\\":[{\\\"label\\\":\\\" 백엔드\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 프론트엔드\\\",\\\"reason\\\":0}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"대기업 가실분\\\",\\\"url\\\":\\\"\\\"}]\"'),(39,150,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOWMAN\\\",\\\"children\\\":[{\\\"label\\\":\\\"일등일등\\\",\\\"reason\\\":0}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"너무 추워요\\\",\\\"url\\\":\\\"\\\"}]\"'),(41,152,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"지도학습\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"비지도학습\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"강화학습\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"인공지능 알고리즘\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(43,154,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"Movavi\\\",\\\"children\\\":[{\\\"label\\\":\\\"폰트 추가\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"참고 영상\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"UCC\\\",\\\"children\\\":[{\\\"label\\\":\\\"UCC 컨텐츠\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 텍스트\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"이미지\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"비디오\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\"플랫폼\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 마케팅\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"전략\\\",\\\"reason\\\":0}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"B급 감성\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"UCC 만들기\\\",\\\"url\\\":\\\"\\\"}]\"'),(44,155,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"기본 원리\\\",\\\"children\\\":[{\\\"label\\\":\\\" 구조물을 이용한 힘 전달\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 무게중심과 체중이동\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"상대방의 반응과 타이밍\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"기술\\\",\\\"children\\\":[{\\\"label\\\":\\\" 시저스 스윕\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 트라이포드 스윕\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"태클\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"포지션\\\",\\\"children\\\":[{\\\"label\\\":\\\"서브미션\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 누르는 서브미션\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 뤼스트락\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"카프락\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\" 펴는 서브미션\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 비트는 서브미션\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"조르는 서브미션\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\"백마운트\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 더블 언더\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 트렁크\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"터틀가드\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\" 사이드\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"클로즈가드\\\",\\\"reason\\\":0}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"주짓수 공부\\\",\\\"url\\\":\\\"\\\"}]\"'),(45,156,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"자소서\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"시험\\\",\\\"children\\\":[{\\\"label\\\":\\\"코테\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 삼성 A, B 형\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"백준 많이 풀기\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 1일 1알고\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\"인적성\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"인적성공부하기\\\",\\\"reason\\\":0}]},{\\\"label\\\":\\\" NCS\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"면접\\\",\\\"children\\\":[{\\\"label\\\":\\\"복장\\\",\\\"reason\\\":0},{\\\"label\\\":\\\"목소리\\\",\\\"reason\\\":0}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"취준 체크리스트\\\",\\\"url\\\":\\\"\\\"}]\"'),(46,157,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"LOL\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"FAKER\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"Movavi\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"관심분야 정리\\\",\\\"url\\\":\\\"\\\"}]\"'),(47,158,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"T1\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"DK\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"FPX\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"롤 팀정리\\\",\\\"url\\\":\\\"\\\"}]\"'),(48,159,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"새\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"조류\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"bird\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"새\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(49,160,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"DB\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SERVER\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"AWS\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"백엔드\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(50,161,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"면접\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"시험\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"자소서\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"취준\\\",\\\"url\\\":\\\"\\\"}]\"'),(52,163,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"알짜배기 루트\\\",\\\"children\\\":[{\\\"label\\\":\\\" 1. 파리\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"2. 스타스부르크\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"3. 니스\\\",\\\"reason\\\":0}]}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"효도 루트\\\",\\\"children\\\":[{\\\"label\\\":\\\" 1. 니스\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"2. 리옹\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 3. 파리\\\",\\\"reason\\\":0}]}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"파리 인/아웃 루트\\\",\\\"children\\\":[{\\\"label\\\":\\\" 1. 파리\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"2. 리옹\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 3. 니스\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 4. 마르세유\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 5. 아비뇽\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 6. 보르도\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 7. 파리\\\",\\\"reason\\\":0}]}]}]}]}]}]}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"프랑스 여행 루트\\\",\\\"url\\\":\\\"\\\"}]\"'),(54,165,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"롤\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"듀오\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"바텀\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"롤\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(55,166,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"먹을거\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"주인\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"산책\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"강아지\\\",\\\"url\\\":\\\"\\\"}]\"'),(56,167,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"지원\\\",\\\"children\\\":[{\\\"label\\\":\\\" 전문화된 SW 교육\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 교육지원금\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 삼성전자 해외연구소 실습 기회\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 우수 교육생 시상\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 개인 맞춤형 진로상담 및 취업지원 서비스\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"로드맵\\\",\\\"children\\\":[{\\\"label\\\":\\\" 1. 기본과정 (5개월)\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 2. 1차 Job Fair (1개월)\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 3. 심화과정 (5개월)\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 4. 2차 Job Fair (1개월)\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"특징\\\",\\\"children\\\":[{\\\"label\\\":\\\"몰입형 집중 코딩 교육\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 실전형 자기주도 학습\\\",\\\"reason\\\":0},{\\\"label\\\":\\\" 성과 창출형 교육\\\",\\\"reason\\\":0}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"SSAFY란?\\\",\\\"url\\\":\\\"\\\"}]\"'),(57,168,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"이슈\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(59,170,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"직군\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"채용\\\",\\\"children\\\":[{\\\"label\\\":\\\"1. 지원서 접수\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"2. 코딩 테스트\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 3. 1차 인터뷰\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"4. 2차 인터뷰\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 5. 최종 합격\\\",\\\"reason\\\":0}]}]}]}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"수시채용\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"카카오\\\",\\\"url\\\":\\\"\\\"}]\"'),(60,171,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"청축\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"적축\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"갈축\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"체리축\\\",\\\"url\\\":\\\"\\\"}]\"'),(61,172,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"서울\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"부산\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"제주\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"한국 관광\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(62,173,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"무료\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"TOP 10\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"익스텐션\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"익스텐션\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(65,176,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"주식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"가이드\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"주식초보\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"주식초보자\\\",\\\"url\\\":\\\"\\\"}]\"'),(67,178,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"강화학습\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"바둑\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"인공지능\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"AlphaGo\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(68,179,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"스윕\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"어드\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"그립싸움\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"델라히바\\\",\\\"url\\\":\\\"\\\"}]\"'),(70,181,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"FAQ\\\",\\\"children\\\":[{\\\"label\\\":\\\" 질문하러가기 GO~\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"지원자격\\\",\\\"children\\\":[{\\\"label\\\":\\\" 해당 분야 전공자. 첨부파일 확인\\\",\\\"reason\\\":0}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"채용 과정\\\",\\\"children\\\":[{\\\"label\\\":\\\" 1. 지원서 접수\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 2. 직무접합성 평가\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\"3. 직무적성검사\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 4. 면접\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 5. 건강검진\\\",\\\"reason\\\":0}]}]}]}]}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"삼성전자\\\",\\\"url\\\":\\\"\\\"}]\"'),(74,185,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"관성\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"가속도\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"작용반작용\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"운동법칙\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(75,186,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"좋은개\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"나쁜주인\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"훈련법\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"개\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(76,187,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"c\\\",\\\"children\\\":[{\\\"label\\\":\\\" speed of light\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" 299,792,458 m/s\\\",\\\"reason\\\":0}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"m\\\",\\\"children\\\":[{\\\"label\\\":\\\" mass\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" kg\\\",\\\"reason\\\":0}]}]},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"e\\\",\\\"children\\\":[{\\\"label\\\":\\\"e\\\",\\\"reason\\\":0,\\\"children\\\":[{\\\"label\\\":\\\" J\\\",\\\"reason\\\":0}]}]}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"e=mc2\\\",\\\"url\\\":\\\"\\\"}]\"'),(88,199,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"맨유\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"토트넘\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"손흥민\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"프리미어리그\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(107,218,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"ㅗ\\\",\\\"url\\\":\\\"\\\"}]\"'),(108,219,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"ㅗ\\\",\\\"url\\\":\\\"\\\"}]\"'),(109,220,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"캡처하고싶다면저장버튼!\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"'),(110,221,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"끝끝\\\",\\\"url\\\":\\\"\\\"}]\"'),(111,222,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"끼야아앙아\\\",\\\"url\\\":\\\"\\\"}]\"'),(112,223,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"끝끝\\\",\\\"url\\\":\\\"\\\"}]\"'),(113,224,NULL,NULL,'\"[{\\\"children\\\":[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"여행지\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"음식\\\"},{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"SNOW\\\"}],\\\"root\\\":\\\"true\\\",\\\"label\\\":\\\"수정은프로필에서됩니다.\\\",\\\"url\\\":\\\"\\\"}]\"'),(114,225,NULL,NULL,'\"[\\r\\n{\\\"children\\\":\\r\\n[{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"개인기\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"탈춤\\\"},\\r\\n{\\\"reason\\\":\\\"0\\\",\\\"label\\\":\\\"드리블\\\"}],\\r\\n\\\"root\\\":\\\"true\\\",\\r\\n\\\"label\\\":\\\"신충현의 축구\\\",\\r\\n\\\"url\\\":\\\"\\\"}\\r\\n]\"');
/*!40000 ALTER TABLE `node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nodelist`
--

LOCK TABLES `nodelist` WRITE;
/*!40000 ALTER TABLE `nodelist` DISABLE KEYS */;
/*!40000 ALTER TABLE `nodelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nodeposition`
--

LOCK TABLES `nodeposition` WRITE;
/*!40000 ALTER TABLE `nodeposition` DISABLE KEYS */;
/*!40000 ALTER TABLE `nodeposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nodestyle`
--

LOCK TABLES `nodestyle` WRITE;
/*!40000 ALTER TABLE `nodestyle` DISABLE KEYS */;
/*!40000 ALTER TABLE `nodestyle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (11,'ssafy','asdf@naver.com',NULL,'김동욱','김동욱',NULL,NULL,NULL,NULL,NULL,NULL),(12,'ssafy2021!@','mindwiki.manager@gmail.com',NULL,'관리자','Wiki Mind',NULL,NULL,NULL,NULL,'#마인드맵',NULL),(13,'qwer1234@','giga111435@gmail.com','01012345678','지선','웅앵웅아',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/202102181231006AC39CAB-AAA0-4F65-BE21-6FF3F6D54BA1.png'),(14,'wjddnd12!@','kjw11036@naver.com','01001001001','김정웅','SSK',NULL,NULL,NULL,NULL,'#가드#손흥민#null','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218123323ssk.png'),(15,'ssafyssafy!1','ybj0749@gmail.com','ㄴㅇㄻㄴㅇㄹ','손흥민팬','신충현',NULL,NULL,NULL,NULL,'','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219005720son.jpeg'),(16,'ef0846d1!','omt1025@gmail.com',NULL,'오민택','오민택',NULL,NULL,NULL,NULL,'#SSAFY#LOL#자소서',NULL),(18,'ssafyssafy!1','mindwiki.yoonho@naver.com','01062318996','황윤호','노원구 페이커',NULL,NULL,NULL,NULL,'#LOL#FAKER#T1','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219021018보노보노.jfif'),(19,'ef0846d1!','alsxor1025@naver.com','01033333333','강형욱','강형욱',NULL,NULL,NULL,NULL,'#강아지#개#축구#백종원#닭도리탕#♥','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218124820강형욱.jpg'),(20,'wjddnd12!@','ssafy4thA204@naver.com','01012345678','싸피맨','진짜백냥',NULL,NULL,NULL,NULL,'#싸피#프로젝트#성공적','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218144107145514867_867681643966039_6830874475385222051_n.jpg'),(21,'wjddnd12!@','ssafy4th@naver.com','01012341234','싸피맨','진짜백냥',NULL,NULL,NULL,NULL,'#싸피#프로젝트#성공적','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218153233145514867_867681643966039_6830874475385222051_n.jpg'),(22,'wjddnd12!@','temp01@naver.com','12312312312','temp01','호호아저씨',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218171349hoho ajoc.jpg'),(23,'wjddnd12!@','temp02@naver.com','123','temp','백엔드 전문가',NULL,NULL,NULL,NULL,'#코딩#알고리즘#자바','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218172315backend str.png'),(24,'wjddnd12!@','temp03@naver.com','123','temp','유럽 배낭여행 가이드',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218172621backpack thumnail.jpg'),(25,'wjddnd12!@','temp04@naver.com','123','temp04','멀티캠퍼스',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218173342ssafy logo.png'),(26,'wjddnd12!@','temp05@naver.com','123','asdf','롤선생',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218173731graph.jpeg'),(27,'wjddnd12!@','temp06@naver.com','123','asdfasdf','멍멍',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/2021021818005065720796_2333780130220049_8948060505012306349_n.jpg'),(28,'wjddnd12!@','ssafy.admin@naver.com','123','ssafy','멀티캠퍼스',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218180331ssafy logo.png'),(29,'wjddnd12!@','temp07@naver.com','123','asdf','감니합사다',NULL,NULL,NULL,NULL,'#가드#손흥민','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218185458thank you.jpg'),(30,'wjddnd12!@','temp08@naver.com','123','asdf','죠르디',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218185951죠르디.jpg'),(31,'wjddnd12!@','temp10@naver.com','123','asdf','키보드 매니아',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218192804image0-42.jpg'),(32,'wjddnd12!@','temp11@naver.com','123','asdf','문화체육관광부',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218193434문화체육관광부.png'),(33,'wjddnd12!@','temp12@naver.com','123','asdf','뇨마드코더',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218193744nomad.jpg'),(34,'wjddnd12!@','temp13@naver.com','123','asdf','Black Belt',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218194059black.png'),(35,'wjddnd12!@','temp14@naver.com','123','asdf','왕개미',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218194551red arrow.jpg'),(36,'wjddnd12!@','temp15@naver.com','123','asdf','AlphaGo',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218195206alphago.jpg'),(37,'wjddnd12!@','temp16@naver.com','123','asdf','삼성전자 채용',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218200244삼성 로고.png'),(38,'wjddnd12!@','temp17@naver.com','123','wj','뉴턴',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218201040뉴턴.jpg'),(39,'wjddnd12!@','temp18@naver.com','123','asdf','강형욱',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218202359강형욱.png'),(40,'wjddnd12!@','temp20@naver.com','123','qwe','아인슈타인',NULL,NULL,NULL,NULL,'#snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210218202724아인슈타인.jpg'),(41,'a1234567-','tlsdlf5@gmail.com',NULL,'신일','쥐대장',NULL,NULL,NULL,NULL,'#snow',NULL),(43,'wjddnd12!@','zzo1@naver.com',NULL,'wjddnd12','zzo1',NULL,NULL,NULL,NULL,'',NULL),(44,'wodbsekd1!','lblbjy@gmail.com','01012345678','wodbsekd','이재윤',NULL,NULL,NULL,NULL,'#Snow','http://i4a204.p.ssafy.io:8000/mindwiki/image/20210219070453A990B2DA-2057-4A47-B5BD-65E146D24DD6.png');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `scraplist`
--

LOCK TABLES `scraplist` WRITE;
/*!40000 ALTER TABLE `scraplist` DISABLE KEYS */;
INSERT INTO `scraplist` VALUES ('kjw11036@naver.com',148,'2021-02-18 12:38:54'),('kjw11036@naver.com',147,'2021-02-18 12:39:04'),('kjw11036@naver.com',146,'2021-02-18 12:39:05'),('kjw11036@naver.com',147,'2021-02-18 12:39:06'),('kjw11036@naver.com',147,'2021-02-18 12:39:06'),('kjw11036@naver.com',147,'2021-02-18 12:39:06'),('kjw11036@naver.com',147,'2021-02-18 12:39:07'),('kjw11036@naver.com',147,'2021-02-18 12:39:07'),('kjw11036@naver.com',147,'2021-02-18 12:39:07'),('kjw11036@naver.com',149,'2021-02-18 12:43:40'),('kjw11036@naver.com',150,'2021-02-18 13:14:23'),('ssafy4th@naver.com',154,'2021-02-18 15:43:14'),('ssafy4th@naver.com',154,'2021-02-18 15:43:15'),('mindwiki.yoonho@naver.com',158,'2021-02-18 16:44:57'),('mindwiki.yoonho@naver.com',157,'2021-02-18 16:45:02'),('mindwiki.yoonho@naver.com',156,'2021-02-18 16:45:25'),('mindwiki.yoonho@naver.com',155,'2021-02-18 16:45:28'),('temp02@naver.com',159,'2021-02-18 17:23:57'),('temp02@naver.com',158,'2021-02-18 17:23:58'),('temp02@naver.com',156,'2021-02-18 17:24:06'),('temp02@naver.com',152,'2021-02-18 17:24:19'),('temp04@naver.com',164,'2021-02-18 17:57:29'),('temp06@naver.com',164,'2021-02-18 17:59:01'),('temp06@naver.com',163,'2021-02-18 17:59:05'),('temp06@naver.com',166,'2021-02-18 18:01:48'),('temp06@naver.com',165,'2021-02-18 18:01:49'),('ssafy.admin@naver.com',167,'2021-02-18 18:11:38'),('ybj0749@gmail.com',158,'2021-02-18 19:03:13'),('ybj0749@gmail.com',185,'2021-02-18 20:16:03'),('ybj0749@gmail.com',167,'2021-02-18 20:21:41'),('mindwiki.yoonho@naver.com',189,'2021-02-19 01:10:42'),('omt1025@gmail.com',189,'2021-02-19 01:10:45'),('temp01@naver.com',189,'2021-02-19 01:10:57'),('temp02@naver.com',189,'2021-02-19 01:11:31'),('temp05@naver.com',187,'2021-02-19 01:14:32'),('temp05@naver.com',187,'2021-02-19 01:14:33'),('temp05@naver.com',187,'2021-02-19 01:14:33'),('temp05@naver.com',187,'2021-02-19 01:14:34'),('temp05@naver.com',187,'2021-02-19 01:14:34'),('temp05@naver.com',187,'2021-02-19 01:14:34'),('temp05@naver.com',187,'2021-02-19 01:14:35'),('temp05@naver.com',187,'2021-02-19 01:14:35'),('temp05@naver.com',187,'2021-02-19 01:14:35'),('temp05@naver.com',187,'2021-02-19 01:14:36'),('temp05@naver.com',187,'2021-02-19 01:14:36'),('temp05@naver.com',187,'2021-02-19 01:14:36'),('temp05@naver.com',187,'2021-02-19 01:14:36'),('temp05@naver.com',187,'2021-02-19 01:14:37'),('temp05@naver.com',187,'2021-02-19 01:14:37'),('temp05@naver.com',189,'2021-02-19 01:14:38'),('temp05@naver.com',189,'2021-02-19 01:14:38'),('temp06@naver.com',167,'2021-02-19 01:15:58'),('temp06@naver.com',181,'2021-02-19 01:16:44'),('giga111435@gmail.com',189,'2021-02-19 01:29:22'),('giga111435@gmail.com',187,'2021-02-19 01:29:25'),('temp07@naver.com',187,'2021-02-19 01:29:29'),('giga111435@gmail.com',186,'2021-02-19 01:29:31'),('kjw11036@naver.com',167,'2021-02-19 01:37:18'),('temp02@naver.com',187,'2021-02-19 01:59:30'),('kjw11036@naver.com',186,'2021-02-19 02:46:39'),('kjw11036@naver.com',185,'2021-02-19 02:46:40'),('kjw11036@naver.com',199,'2021-02-19 03:00:45'),('giga111435@gmail.com',213,'2021-02-19 04:50:20'),('giga111435@gmail.com',185,'2021-02-19 04:50:49'),('giga111435@gmail.com',168,'2021-02-19 05:00:53'),('ybj0749@gmail.com',218,'2021-02-19 05:44:15'),('giga111435@gmail.com',218,'2021-02-19 05:51:00'),('giga111435@gmail.com',219,'2021-02-19 05:51:03'),('ybj0749@gmail.com',219,'2021-02-19 05:52:16'),('giga111435@gmail.com',223,'2021-02-19 06:32:48');
/*!40000 ALTER TABLE `scraplist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `scrapmindbook`
--

LOCK TABLES `scrapmindbook` WRITE;
/*!40000 ALTER TABLE `scrapmindbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `scrapmindbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `temptemp`
--

LOCK TABLES `temptemp` WRITE;
/*!40000 ALTER TABLE `temptemp` DISABLE KEYS */;
/*!40000 ALTER TABLE `temptemp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `timetag`
--

LOCK TABLES `timetag` WRITE;
/*!40000 ALTER TABLE `timetag` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-19 16:09:32
