class CesarCipher

	def CesarCipher.encrypt(key, message)
		chars = ('a'..'z').to_a
		encrypted = ""
		message.each_char do |ch|
			idx = chars.index(ch)+key
			begin
				encrypted += chars[idx]
			rescue Exception => ignored
				idx = ((chars.index(ch)+key) - chars.index('z'))-1
				retry
			end
		end
		return encrypted
	end

	def CesarCipher.decrypt(key, message)
		chars = ('a'..'z').to_a
		decrypted = ""
		message.each_char do |ch|
			idx = chars.index(ch)-key
			decrypted += chars[idx]
		end
		return decrypted
	end
end

case ARGV[0]
	when "enc"
		puts CesarCipher.encrypt((ARGV[1].to_i rescue nil), (ARGV[2] rescue nil))

	when "dec"
		puts CesarCipher.decrypt((ARGV[1].to_i rescue nil), (ARGV[2] rescue nil))
	else
		puts "invalid"
end
