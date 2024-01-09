using System.Security.Cryptography;
using System.Text;

namespace TodoApp.Code;

public class HashingExamples
{
    // Various Hashing methodologies, in order of least to most secure.
    public static string Base64Hashing(string textToHash)
    {
        byte[] inputBytes = Encoding.ASCII.GetBytes(textToHash);
        string base64 = Encoding.ASCII.GetString(inputBytes);
        return base64;
    }
    
    public static string MD5Hash(string str)
    {
        byte[] inputBytes = Encoding.ASCII.GetBytes(str);

        MD5 md5 = MD5.Create();
        byte[] hash = md5.ComputeHash(inputBytes);

        StringBuilder sb = new StringBuilder();
        foreach(byte b in hash)
        {
            sb.Append(b.ToString("X2"));
        }
        return sb.ToString();
    }

    public static string PBKHash(string text, string salt)
    {
        byte[] inputBytes = Encoding.ASCII.GetBytes(text);
        byte[] saltBytes = Encoding.ASCII.GetBytes(salt);

        var hashAl = new HashAlgorithmName("SHA256");

        var hashedText = Rfc2898DeriveBytes.Pbkdf2(inputBytes, saltBytes, 1000, hashAl, 32);

        string hash = Convert.ToBase64String(hashedText);
        return hash;
    }

    public static bool PBKVerify(string text, string salt, string hash)
    {
        byte[] inputBytes = Encoding.ASCII.GetBytes(hash);
        byte[] saltAsbytes = Encoding.ASCII.GetBytes(salt);
        var hashAlgorithm = new HashAlgorithmName("SHA256");

        var hashedTextAsBytes = Rfc2898DeriveBytes.Pbkdf2(inputBytes, saltAsbytes, 1000, hashAlgorithm, 32);

        string hashed = Convert.ToBase64String(hashedTextAsBytes);

        bool res = hashed == hash;
        return res;
    }

    public static string BCryptHash(string text)
    {
        string hash = BCrypt.Net.BCrypt.HashPassword(text);
        return hash;
    }

    public static bool BCryptVerify(string text, string hash)
    {
        bool res = BCrypt.Net.BCrypt.Verify(text, hash);
        return res;
    }

    /********************************************/
    /**************** HELPERS *******************/
    /********************************************/

    /// <summary>
    /// Helper method that takes a string and rotation,
    /// and iterates across each character of the string, "moving" it a number of spaces equal to the rotation.
    /// This is primarily used for rotating Username in order for it to act like an obfuscated salt for hashing.
    /// </summary>
    /// <param name="text">Username</param>
    /// <param name="rotation">Last 4 digits of CPR</param>
    /// <returns></returns>
    public static string RotateString(string text, int rotation)
    {
        if (string.IsNullOrEmpty(text))
        {
            return text;
        }

        int minAscii = 32; // Minimum printable ASCII character
        int maxAscii = 126; // Maximum printable ASCII character
        int alphabetSize = maxAscii - minAscii + 1; // Size of printable ASCII characters

        rotation = rotation % alphabetSize; // Ensure rotation within the printable ASCII range

        char[] chars = text.ToCharArray();

        for (int i = 0; i < chars.Length; i++)
        {
            char c = chars[i];

            if (c >= minAscii && c <= maxAscii) // Check if within printable ASCII range
            {
                int shifted = c + rotation - minAscii;
                shifted = (shifted % alphabetSize + alphabetSize) % alphabetSize + minAscii; // Adjust rotation within printable ASCII range

                chars[i] = (char)shifted;
            }
        }

        return new string(chars);
    }

}

