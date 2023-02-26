"use client";

import BookmarkListItem, { BookmarkListItemProps } from "./BookmarkListItem";

interface BookmarkListBodyProps {
  items?: [BookmarkListItemProps];
}
const BookmarkListBody = (props: BookmarkListBodyProps) => {
  return (
    <div className="list-group">
      {props.items?.map((item, index) => {
        return (
          <BookmarkListItem
            key={index}
            title={item.title}
            url={item.url}
            createdAt={item.createdAt}
            updatedAt={item.updatedAt}
          />
        );
      })}
    </div>
  );
};

export default BookmarkListBody;
