select count(*)
from employee as e
join department as d
on d.id = e.Department_id
where d.name = "managemant"