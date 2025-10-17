SELECT 
    MAX(num) AS num
FROM (SELECT num
From  MyNumbers
GROUP BY 
    num
HAVING 
    COUNT(num) = 1) as n;

