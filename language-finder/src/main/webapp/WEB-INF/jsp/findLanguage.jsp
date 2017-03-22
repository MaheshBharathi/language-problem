<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Language Finder</title>
    </head>
    <body>
        <div><b>${response}</b></div>
        <form action="find" method="post" name="languageForm">
            Type a word to find language: <input name="input" type="text" size="20" value=${input}> <br/>
            <input name="submit" type="submit" value="Find Language" />
        </form>
    </body>
</html>