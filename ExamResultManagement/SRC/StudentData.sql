use demo;

DROP TABLE studata;

CREATE TABLE studata (
  id INT NOT NULL,
  student_name VARCHAR(25) DEFAULT NULL,
  division VARCHAR(10) DEFAULT NULL,
  percent DECIMAL(5,2) DEFAULT NULL,
  total_marks INT DEFAULT NULL,
  computer_marks INT DEFAULT NULL,
  maths_marks INT DEFAULT NULL,
  science_marks INT DEFAULT NULL,
  english_marks INT DEFAULT NULL,
  hindi_marks INT DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO demo.studata(id, student_name, division, percent, total_marks, computer_marks, maths_marks, science_marks, english_marks, hindi_marks) VALUES (1001, 'Jhon Doe', 'First', 91.20, 456, 91, 96, 93, 89, 87);
INSERT INTO demo.studata(id, student_name, division, percent, total_marks, computer_marks, maths_marks, science_marks, english_marks, hindi_marks) VALUES (1002, 'Jane Doe', 'Second', 56.40, 282, 51, 58, 52, 73, 48);
INSERT INTO demo.studata(id, student_name, division, percent, total_marks, computer_marks, maths_marks, science_marks, english_marks, hindi_marks) VALUES (1003, 'Jhon David', 'Third', 43.40, 217, 45, 50, 42, 39, 41);
INSERT INTO demo.studata(id, student_name, division, percent, total_marks, computer_marks, maths_marks, science_marks, english_marks, hindi_marks) VALUES (1004, 'Jane David', 'Fail', 32.20, 161, 23, 20, 30, 34, 54);
