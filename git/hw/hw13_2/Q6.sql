select count(*)
from employee as e
join project as p
on e.Project_id = p.id
where p.name = "android"