using System;
using System.Security.Cryptography;
using System.Text;
using System.IO;
using System.Linq;
using Microsoft.AspNetCore.DataProtection;

namespace TodoApp.Code;

public class EncryptionExamples
{
    
    // Methods for d/encrypting plaintext to and from ciphertext, using the rotated username as key.
    // NOTE: Due to limitations, the rotated username may not always be a valid key, hence the GetValidKey helper function.
    /// <summary>
    /// Encrypts the given plainText using the supplied key
    /// </summary>
    /// <param name="plainText"></param>
    /// <param name="key"></param>
    /// <returns></returns>
    public static string Encrypt(string plainText, string key)
    {
        using (Aes aesAlg = Aes.Create())
        {
            aesAlg.Key = GetValidKey(key);
            aesAlg.GenerateIV();

            ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);

            using (MemoryStream msEncrypt = new MemoryStream())
            {
                using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                {
                    using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                    {
                        swEncrypt.Write(plainText);
                    }
                }
                return Convert.ToBase64String(aesAlg.IV) + Convert.ToBase64String(msEncrypt.ToArray());
            }
        }
    }

    /// <summary>
    /// Decrypst the supplied cipherText using the given key
    /// </summary>
    /// <param name="cipherText"></param>
    /// <param name="key"></param>
    /// <returns></returns>
    public static string Decrypt(string cipherText, string key)
    {
        try
        {
            using (Aes aesAlg = Aes.Create())
            {
                byte[] iv = Convert.FromBase64String(cipherText.Substring(0, 24));
                byte[] cipherBytes = Convert.FromBase64String(cipherText.Substring(24));
                aesAlg.Key = GetValidKey(key);
                aesAlg.IV = iv;

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);

                using (MemoryStream msDecrypt = new MemoryStream(cipherBytes))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            return srDecrypt.ReadToEnd();
                        }
                    }
                }
            }
        }
        catch (CryptographicException)
        {
            // In case of decryption error (e.g., incorrect key or padding issues),
            // return the original encrypted text instead of throwing an exception.
            return "*OBFUSCATED*";
        }
    }
    
    /// <summary>
    /// Helper method that helps convert a non-applicable key(size) to one acceptable by AES.
    /// </summary>
    /// <param name="key"></param>
    /// <returns></returns>
    public static byte[] GetValidKey(string key)
    {
        // Use a cryptographic hash function (e.g., SHA256) to derive a fixed-size key
        using (SHA256 sha256 = SHA256.Create())
        {
            byte[] hashedKey = sha256.ComputeHash(Encoding.UTF8.GetBytes(key));
            return hashedKey.Take(32).ToArray(); // Ensure the key size matches the algorithm (32 bytes for AES-256)
        }
    }
}

