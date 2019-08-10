require "digest"

case ARGV[0]
	when "md5"
		puts Digest::MD5.hexdigest ARGV[1]

	when "sha1"
		puts Digest::SHA1.hexdigest ARGV[1]

	when "sha256"
		puts Digest::SHA2.new(256).hexdigest ARGV[1]

	when "sha384"
		puts Digest::SHA2.new(384).hexdigest ARGV[1]

	when "sha512"
		puts Digest::SHA2.new(512).hexdigest ARGV[1]

	when "rmd160"
		puts Digest::RMD160.hexdigest ARGV[1]	

	else
		puts "unknow command"
end