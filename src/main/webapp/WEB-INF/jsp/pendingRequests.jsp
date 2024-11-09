<%@ page import="java.util.List" %>
<%@ page import="com.uams.entity.request" %>
<%@ page import="com.uams.entity.users" %>
<%@ page import="com.uams.entity.software" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pending Access Requests</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #fff;
            padding: 20px 0;
            background-color: #4CAF50;
            margin-bottom: 30px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            margin: 5px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #45a049;
        }

        form {
            display: inline;
        }

        .no-requests {
            text-align: center;
            font-size: 18px;
            color: #555;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
        }

        .action-buttons button {
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <h2>Pending Access Requests</h2>
    <div class="no-requests">
        <% 
            List<request> pendingRequests = (List<request>) request.getAttribute("pendingRequests");
            if (pendingRequests == null || pendingRequests.isEmpty()) {
        %>
            <p>No pending requests at the moment.</p>
        <% 
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Employee Name</th>
                        <th>Software Name</th>
                        <th>Access Type</th>
                        <th>Reason for Request</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (request req : pendingRequests) {
                            users employee = req.getUser();
                            software software1 = req.getSoftware();
                    %>
                    <tr>
                        <td><%= employee.getUsername() %></td>
                        <td><%= software1.getSoftwareName() %></td>
                        <td><%= req.getAccessType() %></td>
                        <td><%= req.getReason() %></td>
                        <td class="action-buttons">
                            <form action="pendingRequests" method="post">
                                <input type="hidden" name="requestId" value="<%= req.getId() %>">
                                <button type="submit" name="action" value="approve">Approve</button>
                                <button type="submit" name="action" value="reject">Reject</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        <% 
            }
        %>
    </div>
</body>
</html>
