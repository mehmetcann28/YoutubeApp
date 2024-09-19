Projeyi 3 günde bu haline getirebildik zamanı düzgün kullanamadığımızdan bazı eksiklikler oldu en son videoya yorum eklerken userid yi getiremediğimiz için null pointer hatası aldık. Genelde kullandığımız metodları yazdık. Burayı da acele ettiğimiz için chat gpt ye yazdırdık bilginiz olsun.

Bu proje, temel bir video paylaşım platformu oluşturmayı amaçlamaktadır. Kullanıcılar videolar yükleyebilir, videoları beğenebilir, videolara yorum yapabilir ve diğer kullanıcılarla etkileşime girebilir. Proje, yorum ve beğeni sistemleri gibi sosyal medya platformlarında yaygın olarak kullanılan özellikleri içerir. Ayrıca, kullanıcı yönetimi, video kategorilendirme ve video trend algoritması gibi işlevler de mevcuttur.

Proje Yapısı
Controller Katmanı: İlgili servis katmanlarına veri aktarımını sağlar. CommentController, LikeController, UserController, ve VideoController gibi kontrolcüler, kullanıcı isteklerini işler ve ilgili işlemleri başlatır.
DTO (Data Transfer Objects): Kullanıcıdan gelen verileri ya da geri dönen sonuçları kapsüller. İstek ve yanıt için CommentSaveRequestDTO, CommentResponseDTO, UserSaveRequestDTO, VideoResponseDTO gibi sınıflar bulunur.
Entity Sınıfları: Veritabanında saklanan veri yapılarıdır. User, Video, Comment, ve Like gibi sınıflar, bu yapıları temsil eder.
Service Katmanı: İş mantığının bulunduğu katmandır. Veritabanı işlemlerini gerçekleştirir ve controller katmanıyla iletişim sağlar.
Repository Katmanı: Veritabanı işlemlerinin yapıldığı kısımdır. Her entity için bir repository sınıfı bulunur.
Kullanım Kılavuzu
Veritabanı Bağlantısı Kurma: Projeyi başlatmadan önce, ConnectionProvider sınıfı aracılığıyla veritabanı bağlantısı ayarlanmalıdır. Veritabanı bağlantı bilgilerini girin ve tablolara uygun SQL dosyalarını çalıştırın.

Kullanıcı Kaydı:

Kullanıcı kayıt olmak için ad, soyad, e-posta, kullanıcı adı ve şifre bilgilerini girmelidir.
Kayıt işlemi sonrası kullanıcı bilgileri veritabanına kaydedilir.
Giriş Yapma:

Kullanıcı adı ve şifre ile sisteme giriş yapılır.
Giriş yapıldıktan sonra, kullanıcının profil bilgileri ve favori videoları görüntülenebilir.
Video Yükleme:

Kullanıcılar videolarını yükleyebilir. Videolar başlık, açıklama, kategori, izlenme sayısı, beğeni ve dislike sayıları gibi bilgilerle veritabanına kaydedilir.
Videolara Yorum Yapma ve Beğeni Verme:

Kullanıcılar videolara yorum yapabilir ve beğeni ya da dislike bırakabilir. Yorum ve beğeni işlemleri, ilgili video ve kullanıcı bilgileri ile birlikte kaydedilir.
Video Arama:

Kullanıcılar video başlığına göre arama yapabilir. Ayrıca, kategoriye göre videoları filtreleyerek arama yapabilirler.
Trend Videoları Görüntüleme:

İzlenme sayısına göre en popüler videolar trend videolar olarak listelenir. Sistemde en fazla izlenen videolar bu listede gösterilir.
Projeyi çalıştırmak için, gerekli bağımlılıkları yükleyip (JDBC sürücüleri vb.) MainGui sınıfını başlatarak kullanıcı arayüzüne erişebilirsiniz.






Şunu dedin:
service, dto ve controller sınıfında ki metodları detaylıca anlat
ChatGPT:
ChatGPT
Service Sınıfındaki Metodlar
1. UserService Sınıfı Bu sınıf, kullanıcı ile ilgili işlemleri gerçekleştirir. Veritabanı işlemlerini UserRepository aracılığıyla yapar.

saveUser(UserSaveRequestDTO userSaveRequestDTO)

Kullanıcının kaydını veritabanına ekler.
DTO'dan alınan verileri, User entity'sine dönüştürür ve UserRepository.save(user) ile kaydeder.
Eğer kayıt başarılı olursa, kullanıcının ID'sini döner.
getUserById(Long id)

Verilen kullanıcı ID'sine göre veritabanından kullanıcıyı getirir.
Eğer kullanıcı bulunamazsa, hata fırlatır.
login(String username, String password)

Kullanıcı adı ve şifre ile giriş yapar. Veritabanından bu bilgilere sahip kullanıcıyı arar.
Eğer eşleşen kullanıcı bulunursa, giriş başarılı olur, aksi takdirde hata mesajı döner.
2. VideoService Sınıfı Bu sınıf, video yükleme, izleme ve beğeni işlemleri gibi video ile ilgili iş mantığını içerir.

saveVideo(VideoSaveRequestDTO videoSaveRequestDTO)

Kullanıcı tarafından yüklenen videoyu kaydeder. DTO'dan gelen verileri, Video entity'sine dönüştürür ve veritabanına ekler.
getVideoById(Long id)

Verilen ID'ye göre videoyu getirir. Eğer video bulunamazsa hata mesajı verir.
incrementViewCount(Long videoId)

Video izlenme sayısını bir artırır. Her izlenmede bu metod çağrılır ve izlenme sayısı güncellenir.
3. CommentService Sınıfı Yorum işlemleri için kullanılan servis sınıfıdır. Videolara yapılan yorumlar bu sınıf aracılığıyla işlenir.

saveComment(CommentSaveRequestDTO commentSaveRequestDTO)

Videoya yapılan yorumu kaydeder. Kullanıcı, video ve yorum içeriği verilerini DTO'dan alır, entity'ye dönüştürür ve veritabanına kaydeder.
getCommentsByVideoId(Long videoId)

Belirli bir videoya ait tüm yorumları getirir. Video ID'si ile yorumları CommentRepository aracılığıyla bulur ve döner.
4. LikeService Sınıfı Beğeni ve dislike işlemleri için kullanılan servis sınıfıdır.

likeVideo(Long videoId, Long userId)

Kullanıcının bir videoyu beğenmesini sağlar. Eğer daha önce beğenmemişse, yeni bir beğeni kaydı oluşturur.
dislikeVideo(Long videoId, Long userId)

Kullanıcının bir videoyu dislike yapmasını sağlar. Aynı işlemi dislike için gerçekleştirir.
getLikeCount(Long videoId)

Belirli bir videonun toplam beğeni sayısını döner. Video ID'sine göre beğeni sayısı hesaplanır.
DTO (Data Transfer Objects) Sınıfındaki Metodlar
DTO sınıfları, verilerin taşınmasını sağlar ve genellikle kullanıcı isteklerini veya yanıtlarını yönetir. DTO'lar sadece veri taşımak için kullanılan sınıflar olup, iş mantığı içermezler.

1. UserSaveRequestDTO Bu sınıf, kullanıcı kayıt işlemi için gerekli verileri taşır.

getUsername()

Kullanıcının adını döner.
getPassword()

Kullanıcının şifresini döner.
getEmail()

Kullanıcının e-posta adresini döner.
2. VideoSaveRequestDTO Video yükleme işlemleri için kullanılan DTO'dur.

getTitle()

Videonun başlığını döner.
getDescription()

Videonun açıklamasını döner.
getUrl()

Videonun URL'sini döner.
3. CommentSaveRequestDTO Yorum ekleme işlemi için kullanılan DTO'dur.

getVideoId()

Yorumu yapılan videonun ID'sini döner.
getUserId()

Yorumu yapan kullanıcının ID'sini döner.
getContent()

Yorumun içeriğini döner.
4. CommentResponseDTO Yorumları kullanıcıya dönerken kullanılan veri transfer sınıfıdır.

getCommentId()

Yorumun ID'sini döner.
getUserId()

Yorumu yapan kullanıcının ID'sini döner.
getVideoId()

Yorumu yapılan videonun ID'sini döner.
Controller Sınıfındaki Metodlar
Controller sınıfları, kullanıcı isteklerini karşılar ve servis katmanı ile etkileşime geçer.

1. UserController Kullanıcı işlemlerini yöneten controller'dır.

registerUser(UserSaveRequestDTO userSaveRequestDTO)

Yeni kullanıcı kaydı alır ve kullanıcıyı kaydetmek için UserService.saveUser() metodunu çağırır.
Kayıt başarılı olursa, kullanıcı bilgileri döner.
login(String username, String password)

Kullanıcı girişini gerçekleştirir. Kullanıcı adı ve şifreyi alır, UserService.login() metodunu çağırır.
2. VideoController Video işlemlerini yöneten controller'dır.

uploadVideo(VideoSaveRequestDTO videoSaveRequestDTO)

Kullanıcı tarafından video yüklemesini yönetir. Video bilgilerini DTO ile alır ve VideoService.saveVideo() metodunu çağırır.
getVideo(Long videoId)

Belirtilen ID'ye sahip videoyu getirir. Video ID'sini alır ve VideoService.getVideoById() metodunu çağırır.
incrementView(Long videoId)

İzlenme sayısını bir artırır. Video ID'sini alır ve VideoService.incrementViewCount() metodunu çağırır.
3. CommentController Yorum işlemlerini yöneten controller'dır.

addComment(CommentSaveRequestDTO commentSaveRequestDTO)

Videoya yapılan bir yorumu ekler. Yorum bilgilerini DTO ile alır ve CommentService.saveComment() metodunu çağırır.
getComments(Long videoId)

Belirtilen videoya ait tüm yorumları döner. Video ID'sini alır ve CommentService.getCommentsByVideoId() metodunu çağırır.
4. LikeController Beğeni işlemlerini yöneten controller'dır.

likeVideo(Long videoId, Long userId)

Bir videoyu beğenmeyi yönetir. Video ID'si ve kullanıcı ID'si alır, LikeService.likeVideo() metodunu çağırır.
dislikeVideo(Long videoId, Long userId)

Bir videoyu dislike etmeyi yönetir. Video ID'si ve kullanıcı ID'sini alır, LikeService.dislikeVideo() metodunu çağırır.
getLikeCount(Long videoId)

Belirtilen videonun toplam beğeni sayısını döner. LikeService.getLikeCount() metodunu çağırır.