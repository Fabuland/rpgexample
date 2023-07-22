document.getElementById("createCharacterForm").onsubmit = function(event) {
            event.preventDefault(); // Prevent the default form submission behavior
            const form = event.target;

            // Perform an asynchronous form submission using fetch API
            fetch(form.action, {
                method: form.method,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams(new FormData(form))
            }).then(response => {
                if (response.ok) {
                    // Redirect to the home page after success
                    window.location.href = '/';
                } else {
                    // Handle the error or display a message if needed
                    console.error('Character creation failed.');
                }
            }).catch(error => {
                // Handle the error or display a message if needed
                console.error('Error occurred during character creation:', error);
            });
        };