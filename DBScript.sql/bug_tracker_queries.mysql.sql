use bug_tracker;

CREATE TABLE `login` (
  `loginid` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`loginid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into login (loginid,password,name,email,type)values('prakash', 'prakash', 'Prakash Kumar', 'prakash@yahoo.com', 'Admin');

CREATE TABLE `products` (
  `prodid` varchar(10) NOT NULL,
  `prodname` varchar(15) NOT NULL,
  `version` varchar(10) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`prodid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into products values('101', 'JavaEdit', '1.01');
insert into products values('102', 'SoundRX', '2.0.2');
insert into products values('103', 'WordSolution', '3.0.2');

CREATE TABLE `experts` (
  `expertid` varchar(10) NOT NULL,
  `prodid` varchar(10) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`expertid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bug` (
  `bugid` int(20) NOT NULL,
  `authorid` varchar(10) NOT NULL,
  `author` varchar(20) NOT NULL,
  `prodid` varchar(10) NOT NULL,
  `prodname` varchar(25) NOT NULL,
  `env` varchar(15) NOT NULL,
  `type` varchar(15) NOT NULL,
  `description` varchar(100) NOT NULL,
  `rep_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `priority` varchar(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  `assign_to` varchar(10) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`bugid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `solutions` (
  `bugid` int(20) NOT NULL,
  `expertid` varchar(10) NOT NULL,
  `expert` varchar(20) NOT NULL,
  `soln` varchar(200) NOT NULL,
  `soln_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`bugid`,`expertid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

