# Write your MySQL query statement below
select round(avg(b.event_date IS NOT NULL), 2) fraction
From
(
    select player_id,Min(event_date) event_date
    FROM ACTIVITY
    GROUP BY player_id
) a
LEFT JOIN Activity b
ON a.player_id=b.player_id AND
DATEDIFF(a.event_date, b.event_date)=-1;
