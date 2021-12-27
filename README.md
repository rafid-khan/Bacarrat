# Bacarrat
Baccarat Game 

Baccarat is a popular card game played at casinos. 

Each round is played between two hands for the player and the banker. 
There are three possible outcomes to the game: 
- Player wins (the player hand beats the banker hand)
- Banker wins (the banker hand beats the player hand)
- Tie (the player and banker hands are equal to each other)

There are four different players of the game. Each one uses a different betting strategy:
- BankerBetter: always bets one dollar on the banker hand to win
- PlayerBetter: always bets one dollar on the player hand to win
- TieBetter: always bets one dollar on a tie 
- CustomBetter: always bets one dollar on whichever outcome is currently the furthest away from
  the expected winning percentage
  
This is a command-line program, where different games can be simulated depending on the 
given arguments in the configuration in the format of seed # - # of rounds

(e.g. 0-1
      2-3
      3-5
      7-100
      10-1000)
