# Text Editor

**TextEditor**, metin üzerinde temel düzenleme işlemlerini gerçekleştirebilen, geri alma (undo) ve yineleme (redo) özelliklerini destekleyen bir metin düzenleyicisidir. 
Stack (yığın) tabanlı bir yapı kullanılarak geliştirilen bu uygulama, yapılan değişikliklerin izlenmesini ve geri alınabilmesini kolaylaştırır.

##  Özellikler

- Belirtilen konuma **metin ekleme (insert)**
- Belirtilen konumdan itibaren **metin silme (delete)**
- Belirli bir metin parçasını **yeni metin ile değiştirme (replace)**
- Son yapılan işlemi **geri alma (undo)**
- Geri alınan işlemi **yeniden uygulama (redo)**
- **Dosyadan komut okuma** ve uygulama desteği

##  Nasıl Çalışır?

Uygulama, her düzenleme işlemini bir `Action` nesnesi olarak kaydeder:

- Yapılan her işlem `undoStack` yığınına eklenir.
- `undo()` işlemi ile son işlem geri alınır ve `redoStack` yığınına aktarılır.
- `redo()` işlemi ile geri alınan işlem tekrar uygulanır ve tekrar `undoStack` yığınına eklenir.
- Yeni bir işlem yapıldığında `redoStack` temizlenir, çünkü işlem geçmişi değişmiştir.

- ##  Sınıf Yapısı

### `Action` Sınıfı

Bir düzenleme işlemini temsil eder.

**Özellikler:**
- `type`: İşlem türü (`insert`, `delete`, `replace`)
- `text`: İşlemde etkilenen metin
- `position`: İşlemin uygulandığı konum
- `originalText`: (replace işlemi için) Değiştirilen orijinal metin
- `newText`: (replace işlemi için) Yeni eklenen metin

### `TextEditor` Sınıfı

Tüm metin işlemlerini ve işlem geçmişini yöneten temel sınıftır.

**Özellikler:**
- `text`: Düzenlenmekte olan metnin tamamı
- `undoStack`: Yapılan işlemleri geri alabilmek için tutar
- `redoStack`: Geri alınan işlemleri yeniden uygulayabilmek için tutar

**Temel Metotlar:**
- `insert(text, position)`
- `delete(position, length)`
- `replace(newText, position, length)`
- `undo()`
- `redo()`
- `read_from_file(filename)`
