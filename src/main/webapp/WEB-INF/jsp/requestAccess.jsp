<%@ page import="java.util.List" %>
<%@ page import="com.uams.entity.software" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Request</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        /* Container Styling */
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-size: 28px;
            font-weight: 600;
        }

        /* Form Styling */
        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-size: 16px;
            color: #555;
            margin-bottom: 8px;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s, box-shadow 0.3s ease-in-out;
        }

        input[type="text"]:focus,
        select:focus,
        textarea:focus {
            border-color: #007bff;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.2);
            outline: none;
        }

        button[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Responsive Design */
        @media (max-width: 480px) {
            .container {
                padding: 25px;
            }

            h2 {
                font-size: 24px;
            }

            .form-group input,
            .form-group textarea,
            .form-group select {
                padding: 14px;
            }

            button[type="submit"] {
                padding: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Request Access to Software</h2>
        <form action="requestAccess" method="post">
            <div class="form-group">
                <label for="softwareName">Software Name</label>
                <select id="softwareName" name="softwareId" required>
                    <option value="">Select Software</option>
                    <%
                    List<software> softwareList = (List<software>) request.getAttribute("softwareList");
                    if (softwareList != null) {
                        for (software software : softwareList) { %>
                            <option value="<%= software.getId() %>"> <%= software.getSoftwareName() %></option>
                        <%
                        }
                    } %>
                </select>
            </div>

            <div class="form-group">
                <label for="accessType">Access Type</label>
                <select id="accessType" name="accessType" required>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>

            <div class="form-group">
                <label for="reason">Reason for Request</label>
                <textarea id="reason" name="reason" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <button type="submit">Submit Request</button>
            </div>
            <button style="color: #ddd;"><a href="login" style="color: black;">Exit</a></button>
        </form>
    </div>
</body>
</html>
