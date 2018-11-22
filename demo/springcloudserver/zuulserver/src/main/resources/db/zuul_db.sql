/*
 Navicat Premium Data Transfer

 Source Server         : zuul-db
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3307
 Source Schema         : zuul_db

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 17/10/2018 22:25:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` varchar(50) NOT NULL,
  `path` varchar(255) NOT NULL,
  `service_id` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `retryable` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `strip_prefix` int(11) DEFAULT NULL,
  `api_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route
-- ----------------------------
BEGIN;
INSERT INTO `route` VALUES ('book', '/book/**', NULL, 'http://127.0.0.1:8090', 0, 1, 1, NULL);
INSERT INTO `route` VALUES ('k8s-mars', '/harmonycloud/**', NULL, 'http://10.100.100.116:8080', 0, 1, 1, NULL);
INSERT INTO `route` VALUES ('springcloud-api', '/springcloud/**', 'SPRINGCLOUD-API', NULL, 0, 1, 1, NULL);

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
