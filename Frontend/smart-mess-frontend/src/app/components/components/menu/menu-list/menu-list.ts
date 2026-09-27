import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

import { Menu } from '../../../../models/models/menu.model';
import { MenuService } from '../../../../services/services/menu';

@Component({
  selector: 'app-menu-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './menu-list.html',
  styleUrl: './menu-list.css'
})
export class MenuList implements OnInit {

  menus: Menu[] = [];

  constructor(
    private menuService: MenuService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadMenus();
  }

  loadMenus(): void {
    this.menuService.getAll().subscribe({
      next: (data: Menu[]) => {
        this.menus = data;
      },
      error: (error: any) => {
        console.error('Error loading menus:', error);
      }
    });
  }

  deleteMenu(id: number): void {
    if (confirm('Are you sure you want to delete this menu?')) {

      this.menuService.delete(id).subscribe({
        next: () => {
          this.loadMenus();
        },
        error: (error: any) => {
          console.error('Error deleting menu:', error);
        }
      });

    }
  }

  editMenu(id: number): void {
    this.router.navigate(['/menus/edit', id]);
  }
}