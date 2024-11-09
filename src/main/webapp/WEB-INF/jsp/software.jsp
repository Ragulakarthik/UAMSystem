<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Software</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            background-color: #f0f0f0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
        }
        /* Container Styling */
        .container {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff; /* White background */
            border-radius: 8px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
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
        textarea,
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s, box-shadow 0.3s ease-in-out;
        }

        input[type="text"]:focus,
        textarea:focus,
        select:focus {
            border-color: #007bff;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.2);
            outline: none;
        }

        button[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff; /* Blue color */
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }

        /* Checkboxes */
        .form-group input[type="checkbox"] {
            margin-right: 5px;
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
        <h2>Create New Software</h2>

        <form action="software" method="post">
            <div class="form-group">
                <label for="softwareName">Software Name</label>
                <input type="text" id="softwareName" name="softwareName" placeholder="Enter software name" required>
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" rows="4" placeholder="Enter software description" required></textarea>
            </div>

            <div class="form-group">
                <label>Access Levels</label><br>
                <input type="checkbox" id="readAccess" name="accessLevels" value="Read">
                <label for="readAccess">Read</label><br>
                <input type="checkbox" id="writeAccess" name="accessLevels" value="Write">
                <label for="writeAccess">Write</label><br>
                <input type="checkbox" id="adminAccess" name="accessLevels" value="Admin">
                <label for="adminAccess">Admin</label>
            </div>

            <div class="form-group">
                <button type="submit">Create Software</button>
            </div>
            <button style="color: #ddd;"><a href="login" style="color: black;">Exit</a></button>
        </form>
    </div>
</body>
</html>
