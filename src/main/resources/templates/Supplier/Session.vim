let SessionLoad = 1
let s:so_save = &so | let s:siso_save = &siso | set so=0 siso=0
let v:this_session=expand("<sfile>:p")
silent only
cd ~/STUFF/TimsHobbyShop/src/main/resources/templates
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
set shortmess=aoO
badd +43 Catalogue/Index.html
badd +1 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/controller/SupplierController.java
badd +46 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/controller/ItemController.java
badd +11 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/repository/Supplier_Repository.java
badd +18 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/model/Supplier.java
badd +3 Supplier/Profile.html
badd +1 Customer/Profile.html
badd +48 Template.html
badd +8 Other/Login.html
badd +15 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/repository/Customer_Repository.java
badd +1 Supplier/Profile.html
badd +1 Supplier/Index.html
badd +0 ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/model/Catalogue.java
badd +20 Sale/Sale.html
badd +0 Customer/Index.html
argglobal
silent! argdel *
$argadd Catalogue/Index.html
set stal=2
edit Supplier/Index.html
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 33 - ((32 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
33
normal! 09|
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/resources/templates/Supplier | endif
tabedit Supplier/Profile.html
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 27 - ((21 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
27
normal! 063|
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/resources/templates/Supplier | endif
tabedit Customer/Index.html
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 39 - ((38 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
39
normal! 0
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/resources/templates/Supplier | endif
tabedit ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/controller/SupplierController.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 45 - ((34 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
45
normal! 013|
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop | endif
tabedit ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/model/Supplier.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 33 - ((32 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
33
normal! 05|
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop | endif
tabedit ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop/model/Catalogue.java
set splitbelow splitright
set nosplitbelow
set nosplitright
wincmd t
set winminheight=0
set winheight=1
set winminwidth=0
set winwidth=1
argglobal
setlocal fdm=manual
setlocal fde=0
setlocal fmr={{{,}}}
setlocal fdi=#
setlocal fdl=0
setlocal fml=1
setlocal fdn=20
setlocal fen
silent! normal! zE
let s:l = 32 - ((31 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
32
normal! 032|
if has('nvim') | tcd ~/STUFF/TimsHobbyShop/src/main/java/com/csci334/TimsHobbyShop | endif
tabnext 2
set stal=1
if exists('s:wipebuf') && getbufvar(s:wipebuf, '&buftype') isnot# 'terminal'
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20 winminheight=1 winminwidth=1 shortmess=filnxtToOFc
let s:sx = expand("<sfile>:p:r")."x.vim"
if file_readable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &so = s:so_save | let &siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
