# Websocket routes

# Client side

## User topics

### /user/topic/join
Listens to response after sending a */app/join* request. If the response is *USERNAME_TAKEN* a alert will pop up to indicate that the user has to pick another username. Otherwise a list of already connected players is sent to the client.

### /user/topic/cards
Listens to personal card responses, like drawing a new card. The JSON response comes with an intent which indicates what type of change needs to be performed. For example the intent *ADD_CARD* add the card the clients pile of cards.

## Public topics

### /topic/players
Listens to any changes regarding players in a game. Handles players connecting and disconnecting on the client side. Also updates players scores.

### /topic/gamestate
Listens to any changes regarding the state the current game is in, i.e. who played a card already, if cards are revealed, etc. UI updates accordingly.