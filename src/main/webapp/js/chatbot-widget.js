// public/js/chatbot-widget.js
(function () {
    // Tạo iframe
    const chatbotBox = document.createElement('div');
    chatbotBox.id = 'chatbot-box';
    chatbotBox.style.position = 'fixed';
    chatbotBox.style.bottom = '100px';
    chatbotBox.style.right = '20px';
    chatbotBox.style.width = '350px';
    chatbotBox.style.height = '500px';
    chatbotBox.style.border = '1px solid #ccc';
    chatbotBox.style.borderRadius = '12px';
    chatbotBox.style.overflow = 'hidden';
    chatbotBox.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.3)';
    chatbotBox.style.display = 'none';
    chatbotBox.style.zIndex = '998';

    const iframe = document.createElement('iframe');
    iframe.src = 'https://chatbot-9reyajstgbaua6cb5mhhkg.streamlit.app?embed=true';
    iframe.allow = 'camera; microphone; clipboard-read; clipboard-write';
    iframe.style.width = '100%';
    iframe.style.height = '100%';
    iframe.style.border = 'none';

    chatbotBox.appendChild(iframe);
    document.body.appendChild(chatbotBox);

    // Nút toggle
    const toggleBtn = document.createElement('button');
    toggleBtn.id = 'chatbot-toggle';
    toggleBtn.innerText = '💬';
    toggleBtn.style.position = 'fixed';
    toggleBtn.style.bottom = '20px';
    toggleBtn.style.right = '20px';
    toggleBtn.style.width = '60px';
    toggleBtn.style.height = '60px';
    toggleBtn.style.borderRadius = '50%';
    toggleBtn.style.border = 'none';
    toggleBtn.style.backgroundColor = '#007bff';
    toggleBtn.style.color = 'white';
    toggleBtn.style.fontSize = '28px';
    toggleBtn.style.cursor = 'pointer';
    toggleBtn.style.boxShadow = '0 4px 8px rgba(0,0,0,0.2)';
    toggleBtn.style.zIndex = '999';

    toggleBtn.addEventListener('click', function () {
        chatbotBox.style.display = chatbotBox.style.display === 'none' ? 'block' : 'none';
    });

    document.body.appendChild(toggleBtn);
})();
