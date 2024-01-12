## Overview:
The idea behind the project is a ToDo-web server app that allows a single account
to track and maintain a number of different Todo-items, assigned to a number of different sub-users.
I.e. a "family account" where family member 1, 2 and 3, can each have their own ToDo items, where:
1. Name is only viewable by the member in question (due to cryptographically salted encryption)
2. Status is viewable by all members
3. One user (test@test.dk) can thus have ToDo items for Member A, B and C
4. But only when the CPR for Member A is applied, will ToDo items deobfuscate. ToDo items for B and C will remain obfuscated.


- Example of Encryptions:
  - Symmetrical:
    - Todo Name (custom)
    - Todo Status (Protector)
  - Asymmetrical:
    - CPR Address
    
- Example of Hashing:
  - CPR Number

- Environment Security:
  - DataProtection library Public and Private key are granted permanency via storage in local files ("private_key.xml", "public_key.xml").

## Encrypted/Hashed Variables:
- Todo Name:
  - The "name" is salted prior to AES encryption, using a key generated cryptographically via:
    - Caesar Cipher:
      - Rotation: The last 4 digits of CPR number
      - Text: Username (e.g. "test@test.dk")
      - Bounded to AES compatible characters to avoid conflicts when encrypting.
        - Username: "test@test.dk" => "sdrs?sdrs-cj"
    - This is reversed during decryption
    - => thus obfuscating information if the last 4 digits of the CPR are incongruent.

- Todo Status:
  - Uses the AspNetCore DataProtection library to encrypt symmetrically, decrypts directly in view.

- CPR Address:
  - same as Todo Status, but asymmetrically; decrypted during Initialization.

- CPR Number:
  - Hashed using Username + rotation cryptography by last 4 digits.
  - In addition, the uniqueness of CPR is verified by:
    - 1. Searching Database for the last 4 digits
    - 2. Applying caesar cipher on that entry's Username using the integer value of the 4 digits.
    - 3. Applying the resulting rotated string as salt
    - 4. Hashing the "new" CPR number with that salt
    - 5. Comparing wether the result of hashing the new CPR number with the salt derived from the found entrys salt.
    - Note that on first hit, the loop exits, as we only need 1 match.