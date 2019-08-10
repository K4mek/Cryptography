rsa = {
	:main => -> do
		# setting private P and Q
		# two prime numbers p end q
		P, Q = 131, 37
		
		# setting public n
		# n = pq
		n = P*Q

		# setting private PHI_N
		# totiant phi n function
		PHI_N = (P - 1) * (Q - 1)

		# setting public e
		# 1 > e > f(n)
		e = 0
		arr = []
		(2...PHI_N).each do |a|
			bool = false
			(2...a).each do |m| 
				if a % m == 0 then 
					bool = true
					break
				end
			end
			arr.push a unless bool
		end

		loop do
			e = arr[50]
			break if PHI_N % e != 0
		end

		# setting private D
		d = 1
		loop do
			if (d*e) % PHI_N == 1 then
				break
			end
			d += 1
		end

		# encripting
		m, enc = "hello world!", []
		m.each_byte do |b|
			c = (b**e) % n
			enc.push c
		end

 		# decrypting
 		dec = []
 		enc.each do |s|
 			c = (s**d) % n
 			dec.push c.chr
 		end

		puts "private P > #{P}, Q > #{Q}, PHI_N > #{PHI_N} and D > #{d} "
		puts "public n > #{n}, e > #{e}"
		puts "text plain > #{m}"
		puts "text encrypted > #{enc.join}"
		puts "text decrypted > #{dec.join}"
	end
}

rsa[:main].call