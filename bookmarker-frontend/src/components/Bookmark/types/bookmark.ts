export interface Bookmark {
    title: string,
    url: string,
    createdAt: string,
    updatedAt?: string
}

export interface BookmarkData {
    id: number,
    totalPages: number,
    totalElements: number,
    currentPage: number,
    bookmarks: [Bookmark]
}