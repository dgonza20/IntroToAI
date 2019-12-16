using UnityEngine;
using System.Collections;

public class AIScript_DavidGonzales : MonoBehaviour
{

    public CharacterScript mainScript;

    public float[] bombSpeeds;          // Array of floats with each bombs speed
    public float[] buttonCooldowns;     // Array of floats with amount of time till bomb is pushable
    public float playerSpeed;           // Speed that player is moving at
    public int[] beltDirections;        // Array of ints of belt direction, 1 = moving away, 0 = stationary, -1 = moving towards player
    public float[] buttonLocations;     // Array of floats, distance each bomb is from your side

    // Global Variables 
    public int targetBeltBomb = 0;             // Target for which bomb to go to
    public float selfLocation;                 // Location of player 
    public float[] bombDistances;              // Array of floats of each bomb to explode on my side
    public float BombTime;                     // Time for Bomb to explode
    public float PlayerTime;                   // Time for player to bomb

    //
    // Use this for initialization
    void Start()
    {
        mainScript = GetComponent<CharacterScript>();

        if (mainScript == null)
        {
            print("No CharacterScript found on " + gameObject.name);
            this.enabled = false;
        }

        buttonLocations = mainScript.getButtonLocations();

        playerSpeed = mainScript.getPlayerSpeed();
    }


    // Update is called once per frame
    void Update()
    {

        buttonCooldowns = mainScript.getButtonCooldowns();
        beltDirections = mainScript.getBeltDirections();

        //Your AI code goes here

        selfLocation = mainScript.getCharacterLocation();
        buttonLocations = mainScript.getButtonLocations();
        bombSpeeds = mainScript.getBombSpeeds();
        bombDistances = mainScript.getBombDistances();

        float minDistance = 1000;
        int minIndex = 0;
        float curDistance;


        // For every belt location choose the nearest one, that is pushable and moving towards self or standstill
        for (int i = 0; i < beltDirections.Length; i++)
        {
            // Current distance of bomb to myself
            curDistance = Mathf.Abs(buttonLocations[i] - selfLocation);
            // Time it will take the bomb to explode
            BombTime = bombDistances[i] / bombSpeeds[i];
            // Time it will take to reach the bomb
            PlayerTime = Mathf.Abs(mainScript.getCharacterLocation() - buttonLocations[i]) / mainScript.getPlayerSpeed();
            // If the bomb is pushable and at a standstill or heading towards me
            if (buttonCooldowns[i] <= 0 && (beltDirections[i] == -1 || beltDirections[i] == 0))
            {
                // If the current distance to the current bomb is less than the current minimum distance, and is reachable, set as target
                if (curDistance < minDistance && PlayerTime < BombTime)
                {
                    minIndex = i;
                    minDistance = curDistance;
                }
                else
                {
                    // Continue searching    
                    continue;
                }
            }
        }

        targetBeltBomb = minIndex;

        // If the bomb is above the player
        if (buttonLocations[targetBeltBomb] > mainScript.getCharacterLocation())
        {
            mainScript.moveUp();
        }
        else if (buttonLocations[targetBeltBomb] < mainScript.getCharacterLocation())
        {   // Bomb is below player
            mainScript.moveDown();
        }
        else
        {
            mainScript.push();
        }
        mainScript.push();


    }


}
