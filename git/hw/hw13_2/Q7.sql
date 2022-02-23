select p.name, count(*)
from employee as e
join project as p
on e.Project_id = p.id
group by project.name