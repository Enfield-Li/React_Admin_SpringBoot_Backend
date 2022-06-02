SELECT c.*, CONCAT('[', GROUP_CONCAT(DISTINCT cg.group_type_name), ']') AS 'groups' FROM customer c LEFT JOIN customer_group cg ON cg.customer_id = c.id WHERE c.id = 904 GROUP BY c.id;

SELECT c.*, JSON_ARRAYAGG(cg.group_type_name) AS 'groups' FROM customer c LEFT JOIN customer_group cg ON cg.customer_id = c.id WHERE c.id = 904 GROUP BY c.id;

SELECT c.*, GROUP_CONCAT(DISTINCT cg.group_type_name) AS 'groups' FROM customer c LEFT JOIN customer_group cg ON cg.customer_id = c.id WHERE c.id = 904 GROUP BY c.id;