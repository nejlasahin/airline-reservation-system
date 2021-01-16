<body> 
<div id="wrapper">
    <ul class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="panel">
            <div class="sidebar-brand-icon rotate-n-15" style="color: #ff7f00">
                <i class="fas fa-plane"></i>
            </div>
            <div class="sidebar-brand-text mx-3">HAWKEYE</div>
        </a>
        <hr class="sidebar-divider my-0">
        <li class="nav-item">
            <a class="nav-link" href="panel">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Anasayfa</span>
            </a>
        </li>
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            HAVAYOLU FİRMASI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#firma" aria-expanded="true" aria-controls="firma">
                <i class="fas fa-fw fa-cog"></i>
                <span>Firma İşlemleri</span>
            </a>
            <div id="firma" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="firmaekle">Firma Ekle</a>
                    <a class="collapse-item" href="firmaliste">Firmaları Listele</a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ucak" aria-expanded="true" aria-controls="ucak">
                <i class="fas fa-fw fa-wrench"></i>
                <span>Uçak İşlemleri</span>
            </a>
            <div id="ucak" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="ucakekle">Uçak Ekle</a>
                    <a class="collapse-item" href="ucakliste">Uçakları Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            HAVAALANI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#Havaalanı" aria-expanded="true" aria-controls="Havaalanı">
                <i class="fas fa-fw fa-flag"></i>
                <span>Havaalanı İşlemleri</span>
            </a>
            <div id="Havaalanı" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="havaalaniekle">Havaalanı Ekle</a>
                    <a class="collapse-item" href="havaalaniliste">Havaalanlarını Listele</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">ÜLKELER</h6>
                    <a class="collapse-item" href="ulkeekle">Ülke Ekle</a>
                    <a class="collapse-item" href="ulkeliste">Ülkeleri Listele</a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header">ŞEHİRLER</h6>
                    <a class="collapse-item" href="sehirekle">Şehir Ekle</a>
                    <a class="collapse-item" href="sehirliste">Şehirleri Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">

        <div class="sidebar-heading">
            UÇUŞ
        </div>
        <li class="nav-item">
            <a class="nav-link" href="ucusolustur">
                <i class="fas fa-fw fa-map-marked-alt"></i>
                <span>Uçuş Oluştur</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#ucus" aria-expanded="true" aria-controls="ucus">
                <i class="fas fa-fw fa-map"></i>
                <span>Uçuş İşlemleri</span>
            </a>
            <div id="ucus" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="guncelucusliste">Güncel Uçuşları Listele</a>
                    <a class="collapse-item" href="gecmisucusliste">Geçmiş Uçuşları Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            KULLANICI
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#kullanici" aria-expanded="true" aria-controls="kullanici">
                <i class="fas fa-fw fa-users-cog"></i>
                <span>Kullanıcı İşlemleri</span>
            </a>
            <div id="kullanici" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="adminekle">Admin Ekle</a>
                    <a class="collapse-item" href="kullaniciliste">Kullanıcı Listele</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            REZERVASYON
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#rezervasyon" aria-expanded="true" aria-controls="rezervasyon">
                <i class="far fa-fw fa-calendar-alt"></i>
                <span>Rezervasyon İşlemleri</span>
            </a>
            <div id="rezervasyon" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="rezervasyonliste">Rezervasyon Listele</a>
                </div>
            </div>
        </li>       
        <hr class="sidebar-divider">
        
        <div class="sidebar-heading">
            MESAJ
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#mesaj" aria-expanded="true" aria-controls="mesaj">
                <i class="fa-fw far fa-envelope"></i>
                <span>Mesaj İşlemleri</span>
            </a>
            <div id="mesaj" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="mesajliste">Gelen Mesajlar</a>
                    <a class="collapse-item" href="cevapliste">Cevap Listesi</a>
                </div>
            </div>
        </li>
        <hr class="sidebar-divider d-none d-md-block">

        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
