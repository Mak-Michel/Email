import { Component, Input } from '@angular/core';
import { faSave } from '@fortawesome/free-solid-svg-icons';
import { take } from 'rxjs';
import { ProxyService } from 'src/app/Controller/Proxy/proxy.service';

@Component({
  selector: 'app-create-folder',
  templateUrl: './create-folder.component.html',
  styleUrls: ['./create-folder.component.css']
})
export class CreateFolderComponent {

  folderName: string = ""
  Save = faSave

  constructor(private proxy: ProxyService){}

  createFolder(){
    alert(this.folderName)
    let temp = this.folderName
    if(temp.replaceAll(" ", "") == "")
      return
    this.proxy.createNewFolder(this.folderName).pipe(take(1)).subscribe(
      data => {
        alert(data)
        if(data == 'Folder Created Successfully')
          this.proxy.getFolders().push(this.folderName)
      }
    );
  }

}
