// Add functionality to the search button
document.getElementById('search-button').addEventListener('click', () => {
    const query = document.getElementById('search-input').value.trim();
    if (!query) {
        alert('Please enter a word or description');
        return;
    }

    fetch(`/api/words/search?definition=${query}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            return response.json();
        })
        .then(data => {
            const resultsContainer = document.getElementById('results-container');
            resultsContainer.innerHTML = ''; // Clear previous results

            if (data.length === 0) {
                resultsContainer.innerHTML = '<p>No results found</p>';
                return;
            }

            const ul = document.createElement('ul');
            data.forEach(item => {
                const li = document.createElement('li');
                li.textContent = `Word: ${item.word}, Definition: ${item.definition}`;
                ul.appendChild(li);
            });
            resultsContainer.appendChild(ul);
        })
        .catch(error => {
            console.error(error);
            alert('An error occurred while fetching results');
        });
});
