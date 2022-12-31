import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InboxComponent } from './m-components/inbox/inbox.component';
import { TrashComponent } from './m-components/trash/trash.component';
import { DraftComponent } from './m-components/draft/draft.component';
import { ComposeComponent } from './m-components/compose/compose.component';
import { ContactsComponent } from './m-components/contacts/contacts.component';
import { SentComponent } from './m-components/sent/sent.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MainScreenRoutingModule } from './main-screen-routing.module';
import { MainScreenComponent } from './main-screen.component';
import { ButtonComponent } from './m-components/button/button.component';
import { ShowFoldersComponent } from './m-components/show-folders/show-folders.component';
import { FoldersComponent } from './m-components/show-folders/folders/folders.component';
import { CreateFolderComponent } from './m-components/create-folder/create-folder.component';
import { Button2Component } from './m-components/show-folders/folders/button2/button2.component';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    MainScreenComponent,
    InboxComponent,
    TrashComponent,
    DraftComponent,
    ComposeComponent,
    ContactsComponent,
    SentComponent,
    ButtonComponent,
    ShowFoldersComponent,
    FoldersComponent,
    CreateFolderComponent,
    Button2Component,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FontAwesomeModule,
    MainScreenRoutingModule,
    NgxPaginationModule
  ]
})
export class MainScreenModule { }
