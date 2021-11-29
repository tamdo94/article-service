# article-service
I) I developed 2 apis for this task:

- Retrieve list of article information
- Retrieve detail info of specific article

Please access swagger url below for documentation and run the api:

Swagger: [https://article.tvnzone.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config](https://article.tvnzone.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

II) Framework usage

Spring boot, JPA, H2 database.

III) Database
I use 3 table and add some sample data:

- `article` : store article info
- `discount` : store discount info
- `article_discount` : mapping article and discount

Please access h2 console below to check the initial data

H2 DB: [https://article.tvnzone.com/h2-console/login.jsp](https://article.tvnzone.com/h2-console/login.jsp)

JDBC url: `jdbc:h2:mem:testdb`

Username: sa

Password: none

IV) Improvement 

- Add api for discount data, add discount validity time info to article response.
- Pagination for list api (because we have unlimited articles)
- Add api authorization
- Add more test (discount price lower than net price case, ...)
