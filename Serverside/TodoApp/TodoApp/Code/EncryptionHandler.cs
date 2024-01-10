using Microsoft.AspNetCore.DataProtection;
using System.Security.Cryptography;
using System.Text;

namespace TodoApp.Code
{
    public class EncryptionHandler
    {
        private readonly IDataProtector _protector;
        public string PublicKey { get; }
        private string PrivateKey { get; }

        public EncryptionHandler(IDataProtectionProvider protector)
        {
            if (File.Exists("private_key.xml") && File.Exists("public_key.xml"))
            {
                PrivateKey = File.ReadAllText("private_key.xml");
                PublicKey = File.ReadAllText("public_key.xml");
            }
            else
            {
                using (RSACryptoServiceProvider rsa = new RSACryptoServiceProvider())
                {
                    PrivateKey = rsa.ToXmlString(true);
                    File.WriteAllText("private_key.xml", PrivateKey);
                    PublicKey = rsa.ToXmlString(false);
                    File.WriteAllText("public_key.xml", PublicKey);

                }
            }
            _protector = protector.CreateProtector(PrivateKey);
        }
        public string ProtectorEncrypt(string text)
        {
            return _protector.Protect(text);
        }
        public string ProtectorDecrypt(string text)
        {
            
            return _protector.Unprotect(text);
        }

        public string ProtectorEncryptAsym(string text)
        {
            using (RSACryptoServiceProvider rsa = new RSACryptoServiceProvider())
            {
                rsa.FromXmlString(PublicKey);

                byte[] encryptedArray = rsa.Encrypt(Encoding.UTF8.GetBytes(text), false);
                var encryptedString = Convert.ToBase64String(encryptedArray);

                return encryptedString;
            }
        }

        public string ProtectorDecryptAsym(string text)
        {
            using (RSACryptoServiceProvider rsa = new RSACryptoServiceProvider())
            {
                rsa.FromXmlString(PrivateKey);

                byte[] decryptedArray = rsa.Decrypt(Convert.FromBase64String(text), false);
                string decryptedString = Encoding.UTF8.GetString(decryptedArray);

                return decryptedString;
            }
        }
    }
}
