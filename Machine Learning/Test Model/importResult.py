# import json

# # Data nama produk dan harga
# nama_produk = "Bayam Kangkung Sawi Brokoli Kubis Wortel Kentang Kacang_Polong Kacang_Tanah Buncis Labu_Siam Labu_Kuning Lobak_Merah Lobak_Putih Tomat Terong Paprika_Hijau Paprika_Kuning Paprika_Merah Bawang_Bombay Bawang_Putih Bawang_Merah Cabai_Keriting_Merah Cabai_Keriting_Hijau Cabai_Rawit_Hijau Cabai_Rawit_Merah Cabai_Rawit_Kering Mentimun Pare_Paria Jamur Kembang_Kol Daun_Bawang Kemangi Selada Seledri Benih_Kacang_Panjang Benih_Kangkung Benih_Jagung_Manis Benih_Timun Benih_Buncis Cangkul_Sawah Bajak_Singkal Garu_Sisir Garu_Piring Lada Cengkeh Kayu_Manis Kapulaga Kunyit Ubi_Jalar Ubi_Kayu Singkong Talas"
# harga_produk = "14000 13500 7500 35500 14000 16000 15000 58500 30000 33500 18000 11500 36000 30000 15500 13000 37000 80000 79000 20000 36000 32000 36000 38000 29500 78000 69000 35000 20000 152000 17000 31000 44500 5000 13000 21000 46500 14000 13000 10000 35000 72500 830000 9000 149000 199000 22500 87000 9000 9500 6000 2000 24500"

# # Membagi data menjadi list
# list_nama_produk = nama_produk.split()
# list_harga_produk = list(map(int, harga_produk.split()))

# # Menggabungkan data menjadi list of dictionaries
# data_produk_list = [{"namaProduk": nama, "prediksiHarga": harga} for nama, harga in zip(list_nama_produk, list_harga_produk)]

# # Menyimpan data ke dalam file JSON
# file_path = 'data_produk.json'
# with open(file_path, 'w') as json_file:
#     json.dump(data_produk_list, json_file, indent=4)

# print(f'Data berhasil disimpan ke file {file_path}')
