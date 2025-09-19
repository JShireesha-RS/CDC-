select r.contest_id,
ROUND(
    (COUNT(DISTINCT r.user_id) /
    (SELECT COUNT(u.user_id) From Users u))
    *100, 2)
    percentage
    from users u join register r
    on u.user_id=r.user_id
    group by r.contest_id
    order by percentage desc,contest_id;
